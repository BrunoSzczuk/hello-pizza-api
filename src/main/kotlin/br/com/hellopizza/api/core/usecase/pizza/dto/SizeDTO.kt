package br.com.hellopizza.api.core.usecase.pizza.dto

import java.math.BigDecimal

data class SizeSumarryDTO(
    val id: Long,
    val description: String,
    val defaultPrice: BigDecimal = BigDecimal.ZERO
)

