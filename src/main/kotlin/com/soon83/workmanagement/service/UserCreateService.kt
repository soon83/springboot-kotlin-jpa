package com.soon83.workmanagement.service

import com.soon83.workmanagement.dto.UserCreateDto
import com.soon83.workmanagement.exception.BusinessException
import com.soon83.workmanagement.exception.ErrorCode
import com.soon83.workmanagement.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserCreateService(
    private val userRepository: UserRepository
) {

    fun createUser(userCreateDto: UserCreateDto): Long {
        val savedUser = userRepository.save(userCreateDto.toEntity())
        return savedUser.id!!
    }
}