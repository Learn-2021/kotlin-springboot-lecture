package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/response")
@RestController
class ResponseApiController {
    // 1. get 4xx
    @GetMapping
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {
        //1. age == null -> 400
        age?.let {
            //age not null
            if (it < 20)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("20보다 커야 합니다.")
        } ?: run {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null입니다.")
        }

        return ResponseEntity.ok("ok")
    }

    // 2. post 200
    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3. put 201
    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest);
    }

    // 4. delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Nothing> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }
}