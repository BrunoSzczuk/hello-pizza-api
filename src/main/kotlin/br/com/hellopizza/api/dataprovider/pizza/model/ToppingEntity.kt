package br.com.hellopizza.api.dataprovider.pizza.model

import br.com.hellopizza.api.core.domain.pizza.BaseView
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

@Table("topping")
data class ToppingEntity(
    @Id
    val id: UUID,
    val description: String,
    val additionalPrice: BigDecimal = BigDecimal.ZERO,
    val enabled: Boolean = true
) : Serializable, BaseView<UUID>(id)
