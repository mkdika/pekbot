package com.kmklabs.pekbotserver

import com.kmklabs.pekbotserver.Commands.PROMO
import com.kmklabs.pekbotserver.Commands.UNRECOGNIZED
import org.springframework.stereotype.Component

@Component
class CommandParser {

    fun parseCommand(text: String): String {
        return when(parse(text)){
            PROMO -> "Maaf, belum ada promo"
            else -> "Maaf, perintah tidak dimengerti :("
        }
    }

    private fun parse(text: String): Commands {
        text.split(" ").forEach { word ->
            if (word.contains("/")){
                when(word) {
                    "/promo" -> return PROMO
                    else -> return UNRECOGNIZED
                }
            }
        }
        return UNRECOGNIZED
    }
}

enum class Commands {
    PROMO,
    UNRECOGNIZED
}