package br.com.hellopizza.api.dataprovider.pizza.model

import br.com.hellopizza.api.core.domain.customer.Customer
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class PizzaEntity(
    @Id
    val id: Long,
    val size: SizeEntity,
    val toppings: Collection<ToppingEntity>,
    val customer: Customer,
    val initialDate: LocalDateTime = LocalDateTime.now(),
    val finished: Boolean = false,
    val description: String
) {
}
