package com.example.mvc.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.IndexOutOfBoundsException
import java.lang.RuntimeException

@RequestMapping("/api/exception")
@RestController
class ExceptionApiController {
    @GetMapping("/hello")
    fun hello() {
        val list = mutableListOf<String>()
        val temp = list[0]
    }

    //Local Exception Handling
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}