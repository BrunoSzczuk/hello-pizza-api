package br.com.hellopizza.api.core.usecase.pizza

import br.com.hellopizza.api.core.usecase.pizza.dto.DeleteSizeCommand

interface DeleteSizeUseCase {
    suspend fun execute(command: DeleteSizeCommand)
}