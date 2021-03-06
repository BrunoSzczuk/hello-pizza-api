package br.com.hellopizza.api.core.usecase.pizza.dto

import br.com.hellopizza.api.core.usecase.Command
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.*


sealed class SizeCommand(
    @field:NotEmpty(message = "The description is mandatory and cannot be blank.")
    @field:Size(
        message = "The description must have length between $MIN_DESCRIPTION_SIZE and $MAX_DESCRIPTION_SIZE.",
        min = MIN_DESCRIPTION_SIZE,
        max = MAX_DESCRIPTION_SIZE
    )
    open val description: String,
    @field:NotNull(message = "The description is mandatory.")
    @field:Min(message = "The limit of topping must be at least $MIN_TOPPING_LIMIT.", value = MIN_TOPPING_LIMIT)
    @field:Max(message = "The limit of topping must be at most $MAX_TOPPING_LIMIT.", value = MAX_TOPPING_LIMIT)
    open val toppingLimit: Long = 1L,
    @field:Min(message = "The topping price must be at least $MIN_TOPPING_PRICE.", value = MIN_TOPPING_PRICE)
    open val defaultPrice: BigDecimal = BigDecimal.valueOf(MIN_TOPPING_PRICE),
    open val enabled: Boolean
) : Command

data class DeleteSizeCommand(
    @field:NotNull(message = "Id must not be null.")
    val id: UUID
) : Command

data class UpdateSizeCommand(
    @field:NotNull(message = "Id must not be null.")
    val id: UUID,
    override val description: String,
    override val toppingLimit: Long = 1L,
    override val defaultPrice: BigDecimal = BigDecimal.valueOf(MIN_TOPPING_PRICE),
    override val enabled: Boolean = true
) : SizeCommand(description, toppingLimit, defaultPrice, enabled)

data class CreateSizeCommand(
    override val description: String,
    override val toppingLimit: Long = 1L,
    override val defaultPrice: BigDecimal = BigDecimal.valueOf(MIN_TOPPING_PRICE),
    override val enabled: Boolean = true
) : SizeCommand(description, toppingLimit, defaultPrice, enabled)

private const val MIN_DESCRIPTION_SIZE = 3
private const val MIN_TOPPING_LIMIT = 1L
private const val MAX_TOPPING_LIMIT = 10L
private const val MAX_DESCRIPTION_SIZE = 120
private const val MIN_TOPPING_PRICE = 0L