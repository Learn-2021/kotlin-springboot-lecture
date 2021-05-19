package com.example.todoapp.repository

import com.example.todoapp.config.AppConfig
import com.example.todoapp.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
internal class TodoRepositoryTest {
    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun initDatabase() {
        todoRepositoryImpl.todoDatabase.init()
    }

    @Test
    fun saveTest() {
        val todo = Todo().apply {
            this.title = "Test schedule"
            this.description = "Test"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        assertEquals(1, result?.index)
        assertNotNull(result?.createAt)
        assertNotNull(result?.updateAt)
        assertEquals(todo.title, result?.title)
        assertEquals(todo.description, result?.description)
    }

    @Test
    fun updateTest() {
        val todo = Todo().apply {
            this.title = "Test schedule"
            this.description = "Test"
            this.schedule = LocalDateTime.now()
        }
        val save = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply {
            this.index = save?.index
            this.title = "업데이트 일정"
            this.description = "update Test"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(newTodo)

        assertNotNull(result)
        assertEquals(save?.index, result?.index)
        assertEquals(save?.title, result?.title)
        assertEquals(save?.description, result?.description)


    }

    @Test
    fun saveAllTest() {
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "Test schedule"
                this.description = "Test"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "Test schedule"
                this.description = "Test"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "Test schedule"
                this.description = "Test"
                this.schedule = LocalDateTime.now()
            }
        )

        val result = todoRepositoryImpl.saveAll(todoList)

        assertTrue(result)
    }

    @Test
    fun findOneTest() {
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "Test schedule"
                this.description = "Test"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "Test schedule2"
                this.description = "Test"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "Test schedule"
                this.description = "Test"
                this.schedule = LocalDateTime.now()
            }
        )
        todoRepositoryImpl.saveAll(todoList)

        val result = todoRepositoryImpl.findOne(2)

        assertNotNull(result)
        assertEquals(todoList.get(1).title, result?.title)
    }
}