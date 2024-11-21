package com.purplemango.gms.service

import com.purplemango.gms.model.AddUser
import com.purplemango.gms.model.User
import com.purplemango.gms.model.ViewUser
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {
    fun getAllUsers(): Flux<ViewUser>
    fun getAllUsers(page: Pageable): Flux<ViewUser>
    fun getUserByUsername(username: String): Mono<ViewUser>
    fun createUser(user: AddUser): Mono<ViewUser>
}