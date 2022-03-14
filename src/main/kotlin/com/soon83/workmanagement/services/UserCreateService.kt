package com.soon83.workmanagement.services

import com.soon83.workmanagement.interfaces.UserCreateDto
import com.soon83.workmanagement.repositories.UserDto
import com.soon83.workmanagement.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserCreateService(private val userRepository: UserRepository) {

    fun createUser(userCreateDto: UserCreateDto): UserDto? {
        return userRepository.save(userCreateDto.toUserDto())
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "니가 찾는 User 는 여기에 엄서용"
            )
    }
}