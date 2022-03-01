package br.com.hellopizza.api.core.usecase.pizza

import br.com.hellopizza.api.core.usecase.Command
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult
import reactor.core.publisher.Mono

interface SizeResultCommandUseCase<T : Command> {
    fun execute(command: T): Mono<SizeResult>
}