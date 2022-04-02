package br.com.hellopizza.api.core.domain.pizza

import br.com.hellopizza.api.core.domain.IProduct
import br.com.hellopizza.api.core.domain.customer.Customer
import java.math.BigDecimal
import java.time.LocalDateTime

data class Pizza(
        val id: Long,
        val size: Size,
        val toppings: Collection<Topping>,
        val customer: Customer,
        val initialDate: LocalDateTime = LocalDateTime.now(),
        val finished: Boolean = false,
        val description: String
) : IProduct {
    override fun total(): BigDecimal {
        return size.defaultPrice.plus(toppings.sumOf { topping: Topping -> topping.additionalPrice })
    }

    override fun observation(): String {
        return "Pizza observation: $description"
    }
}
