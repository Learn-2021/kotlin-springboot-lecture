package com.example.todoapp.controller

import com.example.todoapp.model.http.TodoDto
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/api/todo")
@RestController
class TodoApiController {
    @GetMapping(path=[""])
    fun read(@RequestParam(required = false) index:Int) {

    }

    @PostMapping
    fun create(@Valid @RequestBody todoDto: TodoDto) {

    }

    @PutMapping
    fun update(@Valid @RequestBody todoDto: TodoDto) {

    }

    @DeleteMapping("/{index}")
    fun delete(@PathVariable("index") _index:Int) {

    }
}