package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.exception.SizeNotFoundException
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.DeleteSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.UpdateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.DeleteSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.UpdateSizeCommand
import org.springframework.stereotype.Service
import java.util.*

@Service
class SizeController(
    private val createSizeUseCase: CreateSizeUseCase,
    private val deleteSizeUseCase: DeleteSizeUseCase,
    private val updateSizeUseCase: UpdateSizeUseCase,
    private val sizeGateway: SizeGateway
) {

    suspend fun create(createSizeCommand: CreateSizeCommand) = createSizeUseCase.execute(createSizeCommand)
    suspend fun update(updateSizeCommand: UpdateSizeCommand) = updateSizeUseCase.execute(updateSizeCommand)
    suspend fun findById(id: UUID): Size = sizeGateway.findById(id)
        .orElseThrow { SizeNotFoundException(id) }

    suspend fun delete(id: UUID) = deleteSizeUseCase.execute(DeleteSizeCommand(id))

}