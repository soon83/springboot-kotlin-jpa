package com.soon83.workmanagement.repository

import com.soon83.workmanagement.interfaces.Gender

data class UserDto(

    var id: Long?,
    var name: String?,
    var age: Int?,
    var gender: Gender?,
    var active: Boolean = true,
) {
    constructor(name: String, age: Int, gender: Gender, active: Boolean): this(
        id = null, name = name, age = age, gender = gender, active = active
    )
}
