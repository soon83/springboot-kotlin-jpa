package com.soon83.workmanagement.services

import com.soon83.workmanagement.interfaces.UserCreateDto
import com.soon83.workmanagement.repositories.UserDto
import com.soon83.workmanagement.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserCreateService(private val userRepository: UserRepository) {

    fun createUser(userCreateDto: UserCreateDto): UserDto {
        return userRepository.save(userCreateDto.toUserDto())
    }
}