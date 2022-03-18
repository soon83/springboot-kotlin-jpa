package com.soon83.workmanagement.repository

import com.soon83.workmanagement.domain.User

interface UserRepositoryCustom {
    fun findUserById(userId: Long): User?
}