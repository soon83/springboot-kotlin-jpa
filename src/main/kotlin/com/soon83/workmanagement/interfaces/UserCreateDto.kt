package com.soon83.workmanagement.interfaces

import com.soon83.workmanagement.repository.UserDto
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

data class UserCreateDto(

    @field: NotBlank
    val name: String?,

    @field: PositiveOrZero
    val age: Int?,

    @field: NotNull
    val gender: Gender?,
) {
    fun toUserDto(): UserDto {
        return UserDto(
            id = null,
            name = name,
            age = age,
            gender = gender
        )
    }
}
