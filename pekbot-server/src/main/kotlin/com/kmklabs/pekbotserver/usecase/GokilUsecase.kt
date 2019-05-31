package com.kmklabs.pekbotserver.usecase

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom
import kotlin.streams.toList

@Component
@ConfigurationProperties(prefix = "gokil")
class GokilUsecase {

    lateinit var data : List<String>

    fun randomGokil(text : String): String {
        val splitText = text.split(" ")
        val users = if (splitText.size > 2 && text.contains("@")) {
            splitText.stream().skip(2).toList().joinToString(" ")
        }else {
            ""
        }
        val index = ThreadLocalRandom.current().nextInt(0, data.size)
        return "${data[index]} $users".trim()
    }
}

