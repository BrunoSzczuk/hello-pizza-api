package br.com.hellopizza.api.core.domain.pizza

import java.math.BigDecimal

data class Topping(
        val id: Long,
        val description: String,
        val additionalPrice: BigDecimal = BigDecimal.ZERO
)
