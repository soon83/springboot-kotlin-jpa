package com.soon83.workmanagement.interfaces

data class UserQueryDto(
    val id: Long?,
    val name: String?,
    val age: Int?,
    val gender: Gender?,
    val active: Boolean = true,
)
