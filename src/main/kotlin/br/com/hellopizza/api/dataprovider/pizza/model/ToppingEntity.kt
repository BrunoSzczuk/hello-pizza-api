package br.com.hellopizza.api.dataprovider.pizza.model

import org.springframework.data.annotation.Id
import java.io.Serializable
import java.math.BigDecimal

data class ToppingEntity(
        @Id
        val id: Long,
        val description: String,
        val additionalPrice: BigDecimal = BigDecimal.ZERO
) : Serializable
