package com.example.todoapp.model.http

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.validation.Validation

internal class TodoDtoTest {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {
        val todoDto = TodoDto().apply {
            title = "테스트"
            description = ""
            schedule = "2020-10-20 13:00:00"
        }

        val result = validator.validate(todoDto)

        assertTrue(result.isEmpty())
    }
}