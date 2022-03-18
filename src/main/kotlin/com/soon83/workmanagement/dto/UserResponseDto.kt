package com.soon83.workmanagement.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.soon83.workmanagement.enumcode.Gender
import com.soon83.workmanagement.domain.User

data class UserResponseDto(

    @field: JsonProperty("userId")
    var id: Long? = null,
    var name: String? = null,
    var age: Int? = null,
    var gender: Gender? = null,
) {

    constructor(userId: Long): this(
        id = userId
    )

    constructor(user: User): this(
        id = user.id,
        name = user.name,
        age = user.age,
        gender = user.gender,
    )
}