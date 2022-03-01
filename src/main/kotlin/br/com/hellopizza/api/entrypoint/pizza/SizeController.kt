package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SizeController(private val createSizeUseCase: CreateSizeUseCase) {

    fun create(createSizeCommand: Mono<CreateSizeCommand>) = createSizeUseCase.execute(createSizeCommand)

}