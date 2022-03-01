package br.com.hellopizza.api.core.gateway

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BasicGateway<T, ID> {
    fun findById(id: ID): Mono<T>
    fun findAll(): Flux<T>
    fun save(data: T): Mono<T>
}