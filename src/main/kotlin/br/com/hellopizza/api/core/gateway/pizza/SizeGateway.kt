package br.com.hellopizza.api.core.gateway.pizza

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.BasicGateway
import java.math.BigDecimal
import java.util.*

interface SizeGateway : BasicGateway<Size, UUID> {
    suspend fun findByDescriptionAndToppingLimitAndDefaultPrice(
        description: String,
        toppingLimit: Long,
        defaultPrice: BigDecimal
    ): Optional<Size>
}