package br.com.hellopizza.api.core.usecase.pizza

import br.com.hellopizza.api.core.usecase.Command
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult

interface SizeResultCommandUseCase<T : Command> {
    suspend fun execute(command: T): SizeResult
}