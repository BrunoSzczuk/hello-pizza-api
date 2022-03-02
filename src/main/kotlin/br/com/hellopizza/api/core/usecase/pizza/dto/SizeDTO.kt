package br.com.hellopizza.api.core.usecase.pizza.dto

import br.com.hellopizza.api.core.domain.pizza.Size
import java.math.BigDecimal

data class SizeSumarryDTO(
    val id: Long,
    val description: String,
    val defaultPrice: BigDecimal = BigDecimal.ZERO
) {
    companion object {

        fun from(size: Size) =
            SizeSumarryDTO(
                id = size.id!!,
                description = size.description,
                defaultPrice = size.defaultPrice,
            )


    }
}

