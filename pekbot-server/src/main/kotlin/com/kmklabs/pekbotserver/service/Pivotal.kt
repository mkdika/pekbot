package com.kmklabs.pekbotserver.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.beans.factory.annotation.Value
import retrofit2.Call

@Component
class Pivotal  {

    lateinit var pivotalHttp: PivotalHttp

    @Value("\${pivotalApiKey}")
    lateinit var pivotalApiKey: String

    fun afterPropertiesSet() {
       pivotalHttp = Retrofit.Builder()
                .baseUrl("https://www.pivotaltracker.com/services/v5/projects")
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
            return getStory(command[2], command[3]).orEmpty()
        } else {
            return "Parameter kurang"
        }
    }

    fun getStory(projectId: String, storyId: String): String? {

        val response = pivotalHttp.getStory(projectId, storyId).execute()

        when (response.code()) {
            200 -> {
                return response.body()
            }
            else -> {
                return response.body()
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
        @GET("{projectId}/stories/{storyId}")
        fun getStory(@Path("projectId") projectId: String, @Path("storyId") storyId: String): Call<String>
    }
}