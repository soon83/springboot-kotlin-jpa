package com.soon83.workmanagement.repository

import com.soon83.workmanagement.interfaces.Gender

data class UserDto(

    var id: Long?,
    val name: String?,
    val age: Int?,
    val gender: Gender?,
    val active: Boolean = true,
)
