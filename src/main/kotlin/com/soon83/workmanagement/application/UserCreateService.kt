package com.soon83.workmanagement.application

import com.soon83.workmanagement.interfaces.UserCreateDto
import com.soon83.workmanagement.repository.UserDto
import com.soon83.workmanagement.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserCreateService(private val userRepository: UserRepository) {

    fun createUser(userCreateDto: UserCreateDto): UserDto {
        return userRepository.save(userCreateDto.toUserDto())
    }
}