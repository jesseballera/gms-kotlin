package com.purplemango.gms.repository

import com.purplemango.gms.model.User
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveMongoRepository<User, String> {

//    fun findAll(): Flux<User>
    @Query("{ id: { \$exists: true }}")
    fun findAll(page: Pageable): Flux<User>
    fun findByUsername(username: String): Mono<User>
    fun existsByUsername(username: String): Mono<Boolean>

}