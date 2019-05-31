package com.kmklabs.pekbotserver.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kmklabs.pekbotserver.model.StoryResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import retrofit2.Call
import retrofit2.http.Query

@Component
class Pivotal: InitializingBean  {

    lateinit var pivotalHttp: PivotalHttp

    @Value("\${pivotalApiKey}")
    lateinit var pivotalApiKey: String


    companion object {
        val projectToId = hashMapOf("web" to "1092060", "content" to "2241857", "homepage" to "2241851", "chat" to "2237399", "quiz" to "2241000")
    }

    override fun afterPropertiesSet() {
       pivotalHttp = Retrofit.Builder()
                .baseUrl("https://www.pivotaltracker.com/")
                .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
                .client(
                        OkHttpClient.Builder()
                                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                                .readTimeout(5000, TimeUnit.MILLISECONDS)
                                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                                .addInterceptor(AuthKeyInterceptor(pivotalApiKey))
                                .addInterceptor(createLoggingInterceptor())
                                .build())
                .build()
                .create(PivotalHttp::class.java)
    }

    fun parseCommand(text: String): String {
        val command = text.split(" ")

        if(command.count() >= 4) {
            val projectId = projectToId.get(command[2])
            if (projectId.isNullOrBlank()) return "project tidak ketemu"
            when (command[3]) {
                "unscheduled", "unstarted", "started", "finished", "accepted", "rejected" -> {
                    return getStoriesByState(projectId, command[3]).orEmpty()
                }
                else -> {
                    return getStory(projectId, command[3]).orEmpty()
                }
            }
        } else {
            return "Parameter kurang"
        }
    }

    fun getStory(projectId: String, storyId: String): String? {

        val response = pivotalHttp.getStory(projectId, storyId).execute()

        return when (response.code()) {
            200 -> {
                "Story Title: ${response.body()?.name}\nStatus: ${response.body()?.currentState}"
            }
            else -> {
                "error: ${response.body().toString()}"
            }
        }
    }

    fun getStoriesByState(projectId: String, state: String): String? {
        val stories = getStories(projectId, state)

        if (stories != null && stories.isNotEmpty()) {
            val responseLines = stories.map {
                "[#${it.id}] ${it.name}"
            }

            return responseLines.joinToString("\n")
        }

        return "No $state stories"
    }

    fun getStories(projectId: String, state: String): List<StoryResponse>? {

        val response = pivotalHttp.getStories(projectId, state).execute()

        return when (response.code()) {
            200 -> {
                return response.body()
            }
            else -> {
                null
            }
        }
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    private class AuthKeyInterceptor(val pivotalApiKey: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val authenticatedRequest = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("X-TrackerToken", pivotalApiKey).build()
            return chain.proceed(authenticatedRequest)
        }
    }

    interface PivotalHttp {
        @GET("services/v5/projects/{projectId}/stories/{storyId}")
        fun getStory(@Path("projectId") projectId: String, @Path("storyId") storyId: String): Call<StoryResponse>

        @GET("services/v5/projects/{projectId}/stories?")
        fun getStories(@Path("projectId") projectId: String, @Query("with_state") state: String): Call<List<StoryResponse>>
    }
}