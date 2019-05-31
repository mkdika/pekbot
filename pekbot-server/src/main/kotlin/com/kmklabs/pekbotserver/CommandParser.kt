package com.kmklabs.pekbotserver

import com.kmklabs.pekbotserver.Commands.*
import com.kmklabs.pekbotserver.service.Pivotal
import com.kmklabs.pekbotserver.usecase.GokilUsecase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommandParser {
    @Autowired
    lateinit var gokilUsecase: GokilUsecase
    @Autowired
    lateinit var pivotal: Pivotal

    fun parseCommand(text: String): String {
        return when (parse(text)) {
            PROMO -> "Maaf, belum ada promo"
            PT -> pivotal.parseCommand(text)
            NASIHAT -> gokilUsecase.randomGokil(text)
            else -> "Maaf, perintah tidak dimengerti :("
        }
    }

    private fun parse(text: String): Commands {
        val command = text.split(" ")

        if(command.count() < 2) {
            return UNRECOGNIZED
        }

        return when (command[1]) {
            "promo" -> PROMO
            "nasihat", "nasehat" -> NASIHAT
            "pt" -> PT
            else -> UNRECOGNIZED
        }
    }
}

enum class Commands {
    PROMO,
    NASIHAT,
    PT,
    UNRECOGNIZED
}