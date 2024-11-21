package com.purplemango.gms.service

import com.purplemango.gms.exception.UserDuplicateException
import com.purplemango.gms.exception.UserNotFoundException
import com.purplemango.gms.model.*
import com.purplemango.gms.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.core.publisher.toMono

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {

    private val logger = KotlinLogging.logger {}
//    private val logMessage: String = "User found $user"
    override fun getAllUsers(): Flux<ViewUser> {
        return userRepository.findAll()
           .doOnNext { user -> logger.info("User found $user") }
           .map { it.toViewUser() }
    }
    override fun getAllUsers(page: Pageable): Flux<ViewUser> {
        return userRepository.findAll()
            .doOnNext { user -> logger.info("User found: $user") }
            .map { it.toViewUser() }
    }

    override fun getUserByUsername(username: String): Mono<ViewUser> {
        return userRepository.findByUsername(username)
            .doOnNext{user -> logger.info("User found $user")}
            .map { it.toViewUser() }
            .switchIfEmpty ( UserNotFoundException(username).toMono() )
    }

    override fun createUser(user: AddUser): Mono<ViewUser> {
        return userRepository.existsByUsername(user.username)
            .filter { !it }
            .map { user.toUser() }
            .flatMap { userRepository.save(it) }
            .map { it.toViewUser() }
            .switchIfEmpty ( UserDuplicateException(user.username).toMono() )

    }
}