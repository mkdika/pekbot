package com.kmklabs.pekbotserver

import com.kmklabs.pekbotserver.Commands.*
import com.kmklabs.pekbotserver.usecase.GokilUsecase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommandParser {

    @Autowired
    lateinit var gokilUsecase: GokilUsecase

    fun parseCommand(text: String): String {
        return when (parse(text)) {
            PROMO -> "Maaf, belum ada promo"
            NASIHAT -> gokilUsecase.randomGokil(text)
            else -> "Maaf, perintah tidak dimengerti :("
        }
    }

    private fun parse(text: String): Commands {
        val command = text.split(" ")

        if(command.count() < 2) {
            return UNRECOGNIZED
        }

        when (command[1]) {
            "promo" -> return PROMO
            "nasihat","nasehat" -> return NASIHAT
            else -> return UNRECOGNIZED
        }

        return UNRECOGNIZED
    }
}

enum class Commands {
    PROMO,
    NASIHAT,
    UNRECOGNIZED
}