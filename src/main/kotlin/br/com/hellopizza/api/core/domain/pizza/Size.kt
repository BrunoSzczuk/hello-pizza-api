package br.com.hellopizza.api.core.domain.pizza

import java.math.BigDecimal

data class Size(
    val id: Long?,
    val description: String,
    val toppingLimit: Long = 1L,
    val defaultPrice: BigDecimal = BigDecimal.ZERO
)
