package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.DeleteSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.DeleteSizeCommand
import org.springframework.stereotype.Service

@Service
class SizeController(private val createSizeUseCase: CreateSizeUseCase,
                     private val deleteSizeUseCase: DeleteSizeUseCase,
                     private val sizeGateway: SizeGateway) {

    suspend fun create(createSizeCommand: CreateSizeCommand) = createSizeUseCase.execute(createSizeCommand)
    suspend fun findById(id: Long): Size = sizeGateway.findById(id)
            .orElseThrow { SizeNotFoundException(id) }

    suspend fun deleteById(id: Long) = deleteSizeUseCase.execute(DeleteSizeCommand(id))

}