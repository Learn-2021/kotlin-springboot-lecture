package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@AutoConfigureMockMvc
@WebMvcTest
internal class ExceptionApiControllerTest {
    @Autowired lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(get("/api/exception/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string("hello"))
            .andDo(print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "hoon")
        queryParams.add("age", "20")

       mockMvc.perform(get("/api/exception")
           .queryParams(queryParams))
           .andExpect(status().isOk)
           .andExpect(content().string("hoon 20"))
           .andDo(print())
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "hoon")
        queryParams.add("age", "1")

        mockMvc.perform(get("/api/exception")
            .queryParams(queryParams))
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("\$.result_code").value("FAIL"))
            .andExpect(jsonPath("\$.errors[0].field").value("age"))
            .andExpect(jsonPath("\$.errors[0].value").value("1"))
            .andDo(print())
    }

    @Test
    fun postTest() {
        val userRequest = UserRequest().apply {
            this.name = "hoon"
            this.age = 10
            this.address = "korea"
            this.phoneNumber = "010-1111-2222"
            this.email = "hoon@inflearn.com"
            this.createAt = "2021-05-19 18:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(post("/api/exception")
            .content(json)
            .contentType("application/json")
            .accept("application/json"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.name").value("hoon"))
            .andExpect(jsonPath("\$.age").value("10"))
            .andExpect(jsonPath("\$.address").value("korea"))
            .andExpect(jsonPath("\$.email").value(userRequest.email))
            .andDo(print())
    }

}