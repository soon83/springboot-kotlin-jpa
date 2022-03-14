package com.soon83.workmanagement.services

import com.soon83.workmanagement.interfaces.Gender
import com.soon83.workmanagement.interfaces.UserQueryDto
import com.soon83.workmanagement.interfaces.UserResponseDto
import com.soon83.workmanagement.repositories.UserDto
import com.soon83.workmanagement.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserQueryService(private val userRepository: UserRepository) {

    fun findAllUsers(): ArrayList<UserDto> {
        return userRepository.userList
    }

    fun findUserById(userId: Long): UserDto {
        return userRepository.userList.find {
            it.id == userId
        } ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "니가 찾는 User 는 여기에 엄서용"
        )
    }
}