package com.soon83.workmanagement.interfaces

import com.soon83.workmanagement.repository.UserDto

data class UserResponseDto(
    var id: Long?,
    var name: String?,
    var age: Int?,
    var gender: Gender?,
    var active: Boolean?,
) {
    constructor(userDto: UserDto) : this(
        id = userDto.id,
        name = userDto.name,
        age = userDto.age,
        gender = userDto.gender,
        active = userDto.active
    )
}