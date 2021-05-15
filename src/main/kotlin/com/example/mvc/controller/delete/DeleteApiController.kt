package com.example.mvc.controller.delete

import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
@RestController
class DeleteApiController {
    /**
     * DELETE method가 가질 수 있는 것
     * 1. path variable
     * 2. request param
     * */

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(@RequestParam("name") _name: String, @RequestParam("age") _age: Int): String {
        println("name:$_name")
        println("age:$_age")
        return _name + " " + _age
    }

    @DeleteMapping("/delete-mapping/{name}/{age}")
    fun deleteMappingPath(@PathVariable name: String, @PathVariable age: Int) : String {
        println("name:$name")
        println("age:$age")
        return name + " " + age
    }

}