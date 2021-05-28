package com.example.todoapp.model.http

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

data class TodoDto(
    var index:Int?=null,
    @field:NotBlank
    var title:String?=null,

    var description:String?=null,

    @field:NotBlank
    var schedule:String?=null,

    var createAt:LocalDateTime?=null,

    var updateAt:LocalDateTime?=null
) {
    @AssertTrue(message = "포맷이 다릅니다.")
    fun validSchedule(): Boolean {
        return try {
            LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            true
        } catch (e: Exception) {
            false
        }
    }
}