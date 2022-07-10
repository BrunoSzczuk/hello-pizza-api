package br.com.hellopizza.api.dataprovider.pizza

import br.com.hellopizza.api.dataprovider.pizza.model.SizeEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface SizeRepository : CoroutineCrudRepository<SizeEntity, UUID> {
    suspend fun findByDescriptionAndToppingLimitAndDefaultPrice(
        description: String,
        toppingLimit: Long,
        defaultPrice: BigDecimal
    ): SizeEntity

    fun findAllByEnabledIsTrueOrEnabledIs(enabled: Boolean): Flow<SizeEntity>
}