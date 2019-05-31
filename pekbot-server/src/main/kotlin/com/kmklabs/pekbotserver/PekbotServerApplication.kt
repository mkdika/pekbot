package com.kmklabs.pekbotserver

import com.kmklabs.pekbotserver.model.HttpRequest
import com.kmklabs.pekbotserver.model.HttpResponse
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class PekbotServerApplication {
}

fun main(args: Array<String>) {
	runApplication<PekbotServerApplication>(*args)
}

@RestController
class PekbotController {

	@PostMapping("/fa9d5e92")
	fun ping(@RequestBody httpRequest: HttpRequest) : ResponseEntity<HttpResponse> {
 		return ResponseEntity.ok(HttpResponse(
			text = "Pong! ${httpRequest.message.sender.displayName}"
		))
	}

	@GetMapping("/health")
	fun health() : ResponseEntity<String> {
		return ResponseEntity.ok("UP")
	}

}
