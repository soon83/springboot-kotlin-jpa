package com.soon83.workmanagement.interfaces

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.soon83.workmanagement.application.UserCreateService
import com.soon83.workmanagement.repository.UserDto
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
internal class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userCreateService: UserCreateService

    @Test
    fun `User 단건 등록`() {
        /**
         * given
         */
        val createdUserId = 1L
        val name = "드록바"
        val age = 20
        val gender = Gender.MALE

        val userCreateDto = UserCreateDto(name = name, age = age, gender = gender)
        given(userCreateService.createUser(userCreateDto))
            .willReturn(UserDto(
                id = createdUserId,
                name = userCreateDto.name,
                age = userCreateDto.age,
                gender = userCreateDto.gender,
            )
        )

        /**
         * when
         */
        val userCreateRequest = jacksonObjectMapper().writeValueAsString(userCreateDto)
        val resultActions = mockMvc.perform(
            post("/api/v1/users")
                .content(userCreateRequest)
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
            .andExpect(jsonPath("\$.name").value(name))
            .andExpect(jsonPath("\$.age").value(age))
            .andExpect(jsonPath("\$.gender").value(gender.name))
            .andExpect(jsonPath("\$.active").value(true))
            .andDo(print())
    }
}
