package com.soon83.workmanagement.service

import com.soon83.workmanagement.enumcode.Gender
import com.soon83.workmanagement.domain.User
import com.soon83.workmanagement.dto.UserCreateDto
import com.soon83.workmanagement.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class UserCreateServiceTest {

    @InjectMocks
    private lateinit var userCreateService: UserCreateService

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var userCreateDto: UserCreateDto

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("001. User 단건 등록 - 필수 파라미터를 다 채움")
    fun test001() {
        /**
         * given
         */
        val name = "이름"
        val age = 5
        val gender = Gender.MALE
        val userCreateDto = UserCreateDto(name = name, age = age, gender = gender)

        given(userRepository.save(any(User::class.java))).willReturn(
            User(
                id = 1L, // createdUserId
                name = name,
                age = age,
                gender = gender
            )
        )

        /**
         * when
         */
        val createdUserId = userCreateService.createUser(userCreateDto)
        println("# createdUserId: $createdUserId")

        /**
         * then
         */
        assertThat(createdUserId).isNotNull
        assertThat(createdUserId).isEqualTo(1L)

        verify(userRepository).save(any(User::class.java))
        verify(userRepository, times(1)).save(any(User::class.java))
    }
}

private fun <T> any(type: Class<T>): T = Mockito.any(type)
private fun <T> any(): T = Mockito.any()