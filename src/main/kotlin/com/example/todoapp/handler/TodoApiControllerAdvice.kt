package com.example.todoapp.handler

import com.example.todoapp.controller.TodoApiController
import com.example.todoapp.model.http.Error
import com.example.todoapp.model.http.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackageClasses = [TodoApiController::class])
class TodoApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach { errorObject ->
            val error = Error().apply {
                field = (errorObject as FieldError).field
                message = errorObject.defaultMessage
                value = errorObject.rejectedValue
            }.apply {
                errors.add(this)
            }
        }

        val errorResponse = ErrorResponse().apply {
            resultCode = "FAIL"
            httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            httpMethod = request.method
            message = ""
            path = request.requestURI
            timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.badRequest().body(errorResponse)
    }
}