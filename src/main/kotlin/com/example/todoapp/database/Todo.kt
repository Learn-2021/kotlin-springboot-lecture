package com.example.todoapp.database

import java.time.LocalDateTime

data class Todo(
    var index: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var schedule: LocalDateTime? = null,
    var createAt: LocalDateTime? = null,
    var updateAt: LocalDateTime? = null
)