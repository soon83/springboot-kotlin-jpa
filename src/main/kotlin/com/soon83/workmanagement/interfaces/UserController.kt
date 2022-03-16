package com.soon83.workmanagement.interfaces

import com.soon83.workmanagement.application.UserCreateService
import com.soon83.workmanagement.application.UserQueryService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userQueryService: UserQueryService,
    private val userCreateService: UserCreateService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun findAllUsers(): ResponseEntity<*> {
        val users = userQueryService.findAllUsers()
            .map { UserResponseDto(it) }

        return ResponseEntity.ok(users)
    }

    @GetMapping("{userId}")
    fun findUserById(@PathVariable userId: Long): ResponseEntity<*> {
        log.debug("# userId: $userId")

        val userResponseDto = userQueryService.findUserById(userId)
            ?.let { UserResponseDto(it) }

        return ResponseEntity.ok(userResponseDto)
    }

    @PostMapping
    fun createUser(@RequestBody userCreateDto: @Valid UserCreateDto): ResponseEntity<*> {
        log.debug("# userCreateDto: $userCreateDto")

        val user = userCreateService.createUser(userCreateDto)
            ?.let { UserResponseDto(it) }

        return ResponseEntity
            .created(getCurrentUri(user.id!!))
            .body(user)
    }

    fun getCurrentUri(id: Long): URI {
        val path = "/$id"
        val requestUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
        log.debug("# requestUri: $requestUri")
        log.debug("# requestUri.indexOf: ${requestUri.indexOf("/", 8)}")
        val uri = URI(requestUri.substring(requestUri.indexOf("/", 8)) + path)
        log.debug("# uri: $uri")
        return uri
    }
}