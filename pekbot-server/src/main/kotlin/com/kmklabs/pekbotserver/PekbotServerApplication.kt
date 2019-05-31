package com.kmklabs.pekbotserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PekbotServerApplication

fun main(args: Array<String>) {
	runApplication<PekbotServerApplication>(*args)
}
