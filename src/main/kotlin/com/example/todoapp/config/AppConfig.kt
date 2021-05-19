package com.example.todoapp.config

import com.example.todoapp.database.TodoDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean(initMethod = "init")
    fun todoDatabase(): TodoDatabase {
        println("[INITIALIZE]")
        return TodoDatabase()
    }
}