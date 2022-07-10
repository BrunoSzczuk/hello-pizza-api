package br.com.hellopizza.api.core.gateway.pizza

import br.com.hellopizza.api.core.domain.pizza.Topping
import br.com.hellopizza.api.core.gateway.BasicGateway
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import java.util.*

interface ToppingGateway : BasicGateway<Topping, UUID> {
    suspend fun findByDescriptionAndAdditionalPrice(description: String, additionalPrice: BigDecimal): Optional<Topping>
    fun findAll(showDisabled: Boolean): Flow<Topping>
}