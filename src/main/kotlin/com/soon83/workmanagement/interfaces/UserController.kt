package com.soon83.workmanagement.interfaces

import com.soon83.workmanagement.repositories.UserDto
import com.soon83.workmanagement.services.UserCreateService
import com.soon83.workmanagement.services.UserQueryService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userQueryService: UserQueryService, private val userCreateService: UserCreateService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun findAllUsers() =
        ResponseEntity.ok(userQueryService.findAllUsers())

    @GetMapping("{userId}")
    fun findUserById(@PathVariable userId: Long): ResponseEntity<UserDto> {
        log.debug("# userId: $userId")
        return ResponseEntity.ok(userQueryService.findUserById(userId))
    }

    @PostMapping
    fun createUser(@Valid @RequestBody userCreateDto: UserCreateDto): ResponseEntity<UserResponseDto> {
        log.debug("# userCreateDto: $userCreateDto")

        userCreateService.createUser(userCreateDto)

        return ResponseEntity.ok(
            UserResponseDto(id = 1, name = null, age = null, gender = null)
        )
    }
}