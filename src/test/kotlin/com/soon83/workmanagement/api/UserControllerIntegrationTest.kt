package com.soon83.workmanagement.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.soon83.workmanagement.dto.UserCreateDto
import com.soon83.workmanagement.enumcode.Gender
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
internal class UserControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @DisplayName("0001. User 단건 등록 - 필수 파라미터를 다 채움")
    fun test0001() {
        /**
         * given
         */
        val name = "드록바"
        val age = 20
        val gender = Gender.MALE
        val userCreateDto = UserCreateDto(name = name, age = age, gender = gender)

        /**
         * when
         */
        val resultActions = mockMvc.perform(
            post("/api/v1/users")
                .content(jacksonObjectMapper().writeValueAsString(userCreateDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        /**
         * then
         */
        resultActions
            .andExpect(status().isCreated)
            .andExpect(header().exists(HttpHeaders.LOCATION))
            .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()))
            .andExpect(jsonPath("\$.success").value(true))
            .andExpect(jsonPath("\$.data.userId").value(1L))
            .andDo(print())
    }
}

private fun <T> any(type: Class<T>): T = Mockito.any(type)
private fun <T> any(): T = Mockito.any()
