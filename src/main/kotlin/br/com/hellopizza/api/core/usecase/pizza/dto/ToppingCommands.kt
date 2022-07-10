package br.com.hellopizza.api.core.usecase.pizza.dto

import br.com.hellopizza.api.core.usecase.Command
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


sealed class ToppingCommand(
    @field:NotEmpty(message = "The description is mandatory and cannot be blank.")
    @field:Size(
        message = "The description must have length between $MIN_DESCRIPTION_TOPPING and $MAX_DESCRIPTION_TOPPING.",
        min = MIN_DESCRIPTION_TOPPING,
        max = MAX_DESCRIPTION_TOPPING
    )
    open val description: String,
    @field:Min(message = "The topping price must be at least $MIN_TOPPING_PRICE.", value = MIN_TOPPING_PRICE)
    open val additionalPrice: BigDecimal = BigDecimal.valueOf(MIN_TOPPING_PRICE),
    open val enabled: Boolean
) : Command

data class DeleteToppingCommand(
    @field:NotNull(message = "Id must not be null.")
    val id: UUID
) : Command

data class UpdateToppingCommand(
    @field:NotNull(message = "Id must not be null.")
    val id: UUID,
    override val description: String,
    override val additionalPrice: BigDecimal = BigDecimal.valueOf(MIN_TOPPING_PRICE),
    override val enabled: Boolean = true
) : ToppingCommand(description, additionalPrice, enabled)

data class CreateToppingCommand(
    override val description: String,
    override val additionalPrice: BigDecimal = BigDecimal.valueOf(MIN_TOPPING_PRICE),
    override val enabled: Boolean = true
) : ToppingCommand(description, additionalPrice, enabled)

private const val MIN_DESCRIPTION_TOPPING = 3
private const val MAX_DESCRIPTION_TOPPING = 120
private const val MIN_TOPPING_PRICE = 0L