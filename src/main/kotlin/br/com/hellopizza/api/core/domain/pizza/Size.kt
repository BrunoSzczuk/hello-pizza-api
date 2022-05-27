package br.com.hellopizza.api.core.domain.pizza

import com.github.f4b6a3.uuid.UuidCreator
import java.math.BigDecimal
import java.util.*

data class Size(
    val id: UUID = UuidCreator.getRandomBased(),
    val description: String,
    val toppingLimit: Long = 1L,
    val defaultPrice: BigDecimal = BigDecimal.ZERO
) : BaseView<UUID>(id) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Size

        if (description != other.description) return false
        if (toppingLimit != other.toppingLimit) return false
        if (defaultPrice != other.defaultPrice) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description.hashCode()
        result = 31 * result + toppingLimit.hashCode()
        result = 31 * result + defaultPrice.hashCode()
        return result
    }
}
