package br.com.hellopizza.api.core.usecase.pizza.dto

import br.com.hellopizza.api.core.domain.pizza.Size
import reactor.core.publisher.Mono
import java.math.BigDecimal

data class SizeSumarryDTO(
    val id: Long,
    val description: String,
    val defaultPrice: BigDecimal = BigDecimal.ZERO
) {
    companion object {

        fun from(size: Mono<Size>) = size.map {
            SizeSumarryDTO(
                id = it.id!!,
                description = it.description,
                defaultPrice = it.defaultPrice,
            )
        }

    }
}

