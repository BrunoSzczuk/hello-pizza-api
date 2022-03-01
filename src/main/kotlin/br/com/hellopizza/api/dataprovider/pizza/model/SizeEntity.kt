package br.com.hellopizza.api.dataprovider.pizza.model

import org.springframework.data.annotation.Id
import java.io.Serializable
import java.math.BigDecimal

data class SizeEntity(
    @Id
    val id: Long? = null,
    val description: String,
    val toppingLimit: Long = 1L,
    val defaultPrice: BigDecimal = BigDecimal.ZERO
) : Serializable
