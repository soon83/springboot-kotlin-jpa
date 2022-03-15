package com.soon83.workmanagement.services

import com.soon83.workmanagement.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserQueryService(private val userRepository: UserRepository) {

    fun findAllUsers() = userRepository.userList

    fun findUserById(userId: Long) = userRepository.userList.find { it.id == userId }
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 User 입니다.")
}