package com.soon83.workmanagement.repository

import com.soon83.workmanagement.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom