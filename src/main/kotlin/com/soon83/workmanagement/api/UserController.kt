package com.soon83.workmanagement.api

import com.soon83.workmanagement.dto.UserCreateDto
import com.soon83.workmanagement.dto.UserResponseDto
import com.soon83.workmanagement.service.UserCreateService
import com.soon83.workmanagement.service.UserQueryService
import com.soon83.workmanagement.utils.HttpUtil.Companion.getCurrentUri
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userQueryService: UserQueryService,
    private val userCreateService: UserCreateService,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun findAllUsers(): ResponseEntity<List<UserResponseDto>> {
        val userResponseDtoList = userQueryService.findAllUsers()
            .map { UserResponseDto(it) }

        return ResponseEntity.ok(userResponseDtoList)
    }

    @GetMapping("{userId}")
    fun findUserById(@PathVariable userId: Long): ResponseEntity<UserResponseDto> {
        log.debug("# userId: $userId")

        val userResponseDto = userQueryService.findUserById(userId)
            ?.let { UserResponseDto(it) }

        return ResponseEntity.ok(userResponseDto)
    }

    @PostMapping
    fun createUser(@RequestBody userCreateDto: @Valid UserCreateDto): ResponseEntity<UserResponseDto> {
        log.debug("# userCreateDto: $userCreateDto")

        val user = userCreateService.createUser(userCreateDto)
        val userResponseDto = UserResponseDto(user)

        return ResponseEntity
            .created(getCurrentUri(userResponseDto.id!!))
            .body(userResponseDto)
    }
}