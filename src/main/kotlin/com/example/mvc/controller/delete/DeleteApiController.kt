package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.min

@Validated
@RequestMapping("/api")
@RestController
class DeleteApiController {
    /**
     * DELETE method가 가질 수 있는 것
     * 1. path variable
     * 2. request param
     * */

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam("name") _name: String,
        @NotNull(message = "age값이 누락되었습니다.")
        @Min(value = 20, message = "age는 20보다 커야 합니다.")
        @RequestParam("age") _age: Int
    ): String {
        println("name:$_name")
        println("age:$_age")
        return _name + " " + _age
    }

    @DeleteMapping("/delete-mapping/{name}/{age}")
    fun deleteMappingPath(
        @PathVariable
        @Size(min = 2, max = 5, message = "name의 길이는 2~5")
        @NotNull
        name: String,
        @NotNull(message = "age값이 누락되었습니다.")
        @Min(value = 20, message = "age는 20보다 커야 합니다.")
        @PathVariable age: Int
    ): String {
        println("name:$name")
        println("age:$age")
        return name + " " + age
    }

}