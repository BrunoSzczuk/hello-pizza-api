package br.com.hellopizza.api.core.usecase.pizza.dto

import br.com.hellopizza.api.core.config.ApplicationCoreProperties
import br.com.hellopizza.api.core.usecase.Command
import reactor.core.CoreSubscriber
import reactor.core.publisher.Mono
import java.math.BigDecimal
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

class CreateSizeCommand(
    @field:NotEmpty(message = "The description is mandatory and cannot be blank.")
    @field:Size(
        message = "The description must have length between $MIN_DESCRIPTION_SIZE and $MAX_DESCRIPTION_SIZE.",
        min = MIN_DESCRIPTION_SIZE,
        max = MAX_DESCRIPTION_SIZE
    )
    val description: String,
    @field:Min(message = "The limit of topping must be at least $MIN_TOPPING_LIMIT.", value = MIN_TOPPING_LIMIT)
    @field:Max(message = "The limit of topping must be at most $MAX_TOPPING_LIMIT.", value = MAX_TOPPING_LIMIT)
    val toppingLimit: Long = 1L,
    @field:Min(message = "The topping price must be at least $MIN_TOPPING_PRICE.", value = MIN_TOPPING_PRICE)
    val defaultPrice: BigDecimal = BigDecimal.valueOf(MIN_TOPPING_PRICE)
) : Command {

}

private const val MIN_DESCRIPTION_SIZE = 3
private const val MIN_TOPPING_LIMIT = 1L
private const val MAX_TOPPING_LIMIT = 10L
private const val MAX_DESCRIPTION_SIZE = 120
private const val MIN_TOPPING_PRICE = 0L