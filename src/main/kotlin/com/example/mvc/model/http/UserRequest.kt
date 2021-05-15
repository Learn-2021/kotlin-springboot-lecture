package com.example.mvc.model.http

data class UserRequest(
    var name: String? = null,
    var age: String? = null,
    var email: String? = null,
    var address: String? = null
)