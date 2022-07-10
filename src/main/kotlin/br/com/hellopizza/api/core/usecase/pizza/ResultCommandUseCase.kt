package br.com.hellopizza.api.core.usecase.pizza

import br.com.hellopizza.api.core.usecase.Command

interface ResultCommandUseCase<T : Command, R> {
    suspend fun execute(command: T): R
}