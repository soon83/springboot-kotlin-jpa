package com.soon83.workmanagement.service

import com.soon83.workmanagement.domain.User
import com.soon83.workmanagement.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserQueryService(
    private val userRepository: UserRepository
) {

    fun findAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun findUserById(userId: Long): User? {
        return userRepository.findUserById(userId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 User 입니다.")
    }
}