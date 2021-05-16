package com.example.mvc.model.http

import com.example.mvc.controller.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

//이 클래스는 snake case로 동작한다는 의미
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest(
    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name: String? = null,

    @field:PositiveOrZero // 0을 포함한 양수
    var age: Int? = null,

    @field:Email
    var email: String? = null,

    @field:NotBlank
    var address: String? = null,

    //@JsonProperty("phone_number")
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")  // 정규식 검증
    var phoneNumber: String? = null,

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    var createAt: String? = null // yyyy-MM-dd HH:mm:ss Ex.2021-01-01 00:00:00
) {
//    Custom Validator로 변경
//    @AssertTrue(message = "생성일자 패턴은 yyyy-MM-dd HH:mm:ss 이어야 합니다.")
//    private fun isValidCreateAt(): Boolean {
//        return try {
//            LocalDateTime.parse(this.createAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }
}