package com.soon83.workmanagement.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.soon83.workmanagement.domain.QUser.user
import com.soon83.workmanagement.domain.User

class UserRepositoryImpl(
    private val query: JPAQueryFactory
) : UserRepositoryCustom {

    override fun findUserById(userId: Long): User? {
        return query
            .selectFrom(user)
            .where(user.id.eq(userId))
            .fetchOne()
    }
}