package br.com.hellopizza.api.core.usecase.pizza.dto

import java.math.BigDecimal
import java.util.*

data class ToppingSummaryDTO(
    val id: UUID,
    val description: String,
    val additionalPrice: BigDecimal = BigDecimal.ZERO,
    val enabled: Boolean = true
)

data class ToppingDTO(
    val id: UUID,
    val description: String,
    val additionalPrice: BigDecimal = BigDecimal.ZERO,
    val enabled: Boolean = true
)

