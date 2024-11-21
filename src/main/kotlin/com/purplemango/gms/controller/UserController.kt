package com.purplemango.gms.controller

import com.purplemango.gms.exception.UserNotFoundException
import com.purplemango.gms.model.AddUser
import com.purplemango.gms.model.ViewUser
import com.purplemango.gms.service.UserService
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/users")
class UserController(val userService: UserService) {
    private  val logger = KotlinLogging.logger {}

    @GetMapping
    fun getAllUsers(@RequestParam(value = "page") page: Int?, @RequestParam(value = "count") size: Int?): Flux<ViewUser> {
        return if (page != null && size != null) {
            val pageable = PageRequest.of(page, size)
            logger.info { "PageRequest: $pageable" }
            userService.getAllUsers(pageable)
        } else {
            userService.getAllUsers()
        }
    }

    @GetMapping("/find-by-username")
    fun viewUserByUsername(@RequestParam(value = "username") username: String) = userService.getUserByUsername(username)

    @PostMapping
    fun createUser(@RequestBody user: AddUser) = userService.createUser(user)


    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun userNotFoundException(ex: UserNotFoundException): Mono<String> {
        return Mono.just("User not found")
    }

}