package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import org.springframework.stereotype.Service

@Service
class SizeController(private val createSizeUseCase: CreateSizeUseCase) {

    fun create(createSizeCommand: CreateSizeCommand) = createSizeUseCase.execute(createSizeCommand)

}