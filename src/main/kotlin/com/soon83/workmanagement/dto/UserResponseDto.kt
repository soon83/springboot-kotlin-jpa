package com.soon83.workmanagement.dto

import com.soon83.workmanagement.domain.Gender
import com.soon83.workmanagement.domain.User

data class UserResponseDto(
    val id: Long?,
    val name: String?,
    val age: Int?,
    val gender: Gender?,
) {
    constructor(user: User) : this(
        id = user.id,
        name = user.name,
        age = user.age,
        gender = user.gender,
    )
}