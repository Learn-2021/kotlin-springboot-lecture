package com.example.todoapp.database

data class TodoDatabase(
    var index: Int = 0,
    var todoList: MutableList<Todo> = mutableListOf()
) {
    fun init() {
        index = 0
        this.todoList = mutableListOf()
        println("[DEBUG] todo database init")
    }
}