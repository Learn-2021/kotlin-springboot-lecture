package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
@RestController
class GetApiController {
    @GetMapping(path =["/hello", "/abdc"])
    fun hello(): String {
        return "Hello Kotlin"
    }

    //RequestMapping은 메소드를 지정하지 않으면 모든 요청을 처리함.
    @RequestMapping(method=[RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mappinig/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable(value = "name") _name: String, @PathVariable age: Int): String {
        println("$_name, $age")
        return _name
    }

    //Query parameter
    @GetMapping("/get-mapping/query-param") //?name=hoon&age=10
    fun queryParam(
        @RequestParam name: String, @RequestParam age: Int): String {
        println("$name, $age")
        return name + " " + age
    }

    //객체로 바인딩하기
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map.get("phone-number")
        println("phone: $phoneNumber")
        return map
    }
}