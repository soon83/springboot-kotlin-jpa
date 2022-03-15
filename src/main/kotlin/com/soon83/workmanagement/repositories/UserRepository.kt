package com.soon83.workmanagement.repositories

import com.soon83.workmanagement.interfaces.Gender
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    val userList = arrayListOf(
        UserDto(1, "드록바", 20, Gender.MALE, true),
        UserDto(2, "에투", 22, Gender.MALE, true),
    )

    fun save(userDto: UserDto): UserDto {
        val activeUserCount = this.userList.count { it.active }
        userDto.id = activeUserCount.toLong() + 1
        this.userList.add(userDto)

        return userDto
    }
}