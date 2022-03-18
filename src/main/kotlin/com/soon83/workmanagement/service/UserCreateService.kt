package com.soon83.workmanagement.service

import com.soon83.workmanagement.domain.User
import com.soon83.workmanagement.dto.UserCreateDto
import com.soon83.workmanagement.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserCreateService(
    private val userRepository: UserRepository
) {

    fun createUser(userCreateDto: UserCreateDto): User {
        return userRepository.save(userCreateDto.toEntity())
    }
}