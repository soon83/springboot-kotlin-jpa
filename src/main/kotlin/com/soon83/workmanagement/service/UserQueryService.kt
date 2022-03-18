package com.soon83.workmanagement.service

import com.soon83.workmanagement.dto.UserResponseDto
import com.soon83.workmanagement.exception.BusinessException
import com.soon83.workmanagement.exception.ErrorCode
import com.soon83.workmanagement.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserQueryService(
    private val userRepository: UserRepository
) {

    fun findAllUsers(): List<UserResponseDto> {
        return userRepository.findAll()
            .map { UserResponseDto(it) }
    }

    fun findUserById(userId: Long): UserResponseDto? {
        return userRepository.findUserById(userId)
            ?.let { UserResponseDto(it) }
            ?: throw BusinessException(ErrorCode.NOT_FOUND_USER)
    }
}