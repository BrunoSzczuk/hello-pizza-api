package br.com.hellopizza.api.dataprovider.pizza.model

import br.com.hellopizza.api.core.domain.pizza.BaseView
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.math.BigDecimal
import java.util.*


@Table("size")
data class SizeEntity(
    @Id
    val id: UUID,
    val description: String,
    val toppingLimit: Long = 1L,
    val defaultPrice: BigDecimal = BigDecimal.ZERO,
    val enabled: Boolean = true
) : Serializable, BaseView<UUID>(id)
