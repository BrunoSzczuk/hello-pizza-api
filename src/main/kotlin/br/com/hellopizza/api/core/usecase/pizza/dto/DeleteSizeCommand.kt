package br.com.hellopizza.api.core.usecase.pizza.dto

import br.com.hellopizza.api.core.usecase.Command
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull


data class DeleteSizeCommand(
        @field:Min(1)
        @field:NotNull(message = "Id must not be null.")
        val id: Long
) : Command
