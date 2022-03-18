package com.soon83.workmanagement.api

import com.soon83.workmanagement.dto.Res
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
    fun findAllUsers(): ResponseEntity<Res<List<UserResponseDto>>> {
        val userResponseDtoList = userQueryService.findAllUsers()

        return ResponseEntity.ok(Res.success(userResponseDtoList))
    }

    @GetMapping("{userId}")
    fun findUserById(@PathVariable userId: Long): ResponseEntity<Res<UserResponseDto?>> {
        log.debug("# userId: $userId")

        val userResponseDto = userQueryService.findUserById(userId);

        return ResponseEntity.ok(Res.success(userResponseDto))
    }

    @PostMapping
    fun createUser(@Valid @RequestBody userCreateDto: UserCreateDto): ResponseEntity<Res<UserResponseDto>> {
        log.debug("# userCreateDto: $userCreateDto")

        val createdUserId = userCreateService.createUser(userCreateDto)

        return ResponseEntity
            .created(getCurrentUri(createdUserId))
            .body(Res.success(UserResponseDto(createdUserId)))
    }
}