package com.soon83.workmanagement.dto

import com.soon83.workmanagement.enumcode.Gender
import com.soon83.workmanagement.domain.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

data class UserCreateDto(

    @field: NotBlank
    val name: String? = null,

    @field: NotNull
    @field: PositiveOrZero
    val age: Int? = null,

    @field: NotNull
    val gender: Gender? = null,
) {
    fun toEntity(): User {
        return User(
            name = name,
            age = age,
            gender = gender
        )
    }
}
