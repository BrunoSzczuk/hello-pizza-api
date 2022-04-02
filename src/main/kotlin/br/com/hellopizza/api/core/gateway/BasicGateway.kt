package br.com.hellopizza.api.core.gateway

import kotlinx.coroutines.flow.Flow
import java.util.*

interface BasicGateway<T, ID> {
    suspend fun findById(id: ID): Optional<T>
    suspend fun deleteById(id: ID)
    fun findAll(): Flow<T>
    suspend fun save(data: T): T
}