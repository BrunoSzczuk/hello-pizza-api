package br.com.hellopizza.api.dataprovider.pizza

import br.com.hellopizza.api.dataprovider.pizza.model.ToppingEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface ToppingRepository : CoroutineCrudRepository<ToppingEntity, UUID> {
    suspend fun findByDescriptionAndAdditionalPrice(description: String, additionalPrice: BigDecimal): ToppingEntity
    fun findAllByEnabledIsTrueOrEnabledIs(enabled: Boolean): Flow<ToppingEntity>
}