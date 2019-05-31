package com.kmklabs.pekbotserver

import com.kmklabs.pekbotserver.model.HttpRequest
import com.kmklabs.pekbotserver.model.HttpResponse
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.fasterxml.jackson.databind.ObjectMapper

@SpringBootApplication
class PekbotServerApplication {
}

fun main(args: Array<String>) {
	runApplication<PekbotServerApplication>(*args)
}

@RestController
class PekbotController {

	companion object {
		val logger: Log = LogFactory.getLog(this::class.java)
		val mapper = ObjectMapper()
	}

	@PostMapping("/fa9d5e92")
	fun ping(@RequestBody httpRequest: HttpRequest) : ResponseEntity<HttpResponse> {

		logger.info("\n ${mapper.writerWithDefaultPrettyPrinter().writeValueAsString(httpRequest)}")

 		return ResponseEntity.ok(HttpResponse(
			text = "Pong! ${httpRequest.message.sender.displayName}"
		))
	}

	@GetMapping("/health")
	fun health() : ResponseEntity<String> {

		logger.info("\n ${mapper.writerWithDefaultPrettyPrinter().writeValueAsString(httpRequest)}")

		return ResponseEntity.ok("UP")
	}
}

