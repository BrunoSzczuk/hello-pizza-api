package br.com.hellopizza.api.core.usecase.pizza.dto

import java.math.BigDecimal
import java.util.*

data class SizeSumarryDTO(
        val id: UUID,
        val description: String,
        val defaultPrice: BigDecimal = BigDecimal.ZERO
)

