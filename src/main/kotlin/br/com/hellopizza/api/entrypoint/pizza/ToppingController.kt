package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.exception.ToppingNotFoundException
import br.com.hellopizza.api.core.gateway.pizza.ToppingGateway
import br.com.hellopizza.api.core.usecase.pizza.CreateToppingUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateToppingCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.summaryDTO
import br.com.hellopizza.api.core.usecase.pizza.dto.toppingDTO
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import java.util.*

@Service
class ToppingController(
    private val createToppingUseCase: CreateToppingUseCase,
    private val toppingGateway: ToppingGateway
) {

    suspend fun create(createToppingCommand: CreateToppingCommand) = createToppingUseCase.execute(createToppingCommand)
    fun findAll(showDisabled: Boolean) = toppingGateway.findAll(showDisabled).map { it.summaryDTO() }
    suspend fun findById(id: UUID) =
        toppingGateway.findById(id).orElseThrow { throw ToppingNotFoundException(id) }.toppingDTO()
}