package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

//이 클래스는 snake case로 동작한다는 의미
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest(
    var name: String? = null,
    var age: String? = null,
    var email: String? = null,
    var address: String? = null,
    //@JsonProperty("phone_number")
    var phoneNumber: String? = null
)