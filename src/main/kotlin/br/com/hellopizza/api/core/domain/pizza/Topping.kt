package br.com.hellopizza.api.core.domain.pizza

import com.github.f4b6a3.uuid.UuidCreator
import java.math.BigDecimal
import java.util.*

data class Topping(
    val id: UUID = UuidCreator.getRandomBased(),
    val description: String,
    val additionalPrice: BigDecimal = BigDecimal.ZERO,
    val enabled: Boolean = true
) : BaseView<UUID>(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Topping

        if (description != other.description) return false
        if (additionalPrice != other.additionalPrice) return false
        if (enabled != other.enabled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description.hashCode()
        result = 31 * result + additionalPrice.hashCode()
        result = 31 * result + enabled.hashCode()
        return result
    }
}
