package com.soon83.workmanagement.application

import com.soon83.workmanagement.interfaces.Gender
import com.soon83.workmanagement.interfaces.UserCreateDto
import com.soon83.workmanagement.repository.UserDto
import com.soon83.workmanagement.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.times
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class UserCreateServiceTest {

    @InjectMocks
    private lateinit var userCreateService: UserCreateService

    @Mock
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        val name = "이름"
        val age = 5
        val gender = Gender.MALE

        val request = UserCreateDto(name = name, age = age, gender = gender)
        val toUserDto = request.toUserDto()
        val userDto = UserDto(id = 1L, name = toUserDto.name, age = toUserDto.age, gender = toUserDto.gender, active = toUserDto.active)

        `when`(userRepository.save(any(UserDto::class.java))).thenReturn(userDto)
    }

    @Test
    fun `사용자 단건 등록`() {
        val name = "이름"
        val age = 5
        val gender = Gender.MALE

        val request = UserCreateDto(name = name, age = age, gender = gender)

        val createdUser = userCreateService.createUser(request)
        println(createdUser)

        assertThat(createdUser).isNotNull
        assertThat(createdUser.id).isEqualTo(1L)

        verify(userRepository).save(any())
        verify(userRepository, times(1)).save(any(UserDto::class.java))
    }
}

private fun <T> any(type: Class<T>): T = Mockito.any(type)
private fun <T> any(): T = Mockito.any()