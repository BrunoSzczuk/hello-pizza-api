package br.com.hellopizza.api.core.gateway

import java.util.*

interface BasicGateway<T, ID> {
    suspend fun findById(id: ID): Optional<T>
    suspend fun deleteById(id: ID)
    suspend fun save(data: T): T
}