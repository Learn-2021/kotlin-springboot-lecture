package com.example.mvc.controller.post

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
@RestController
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(): String {
        return "post-mapping"
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    // object mapper는 json <-> object를 변환하는 매퍼
    @PostMapping("/post-mapping/object")
    fun objectMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
        //json -> object
        println(userRequest)
        //object -> json
        return userRequest
    }

}