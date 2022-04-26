package br.com.hellopizza.api.core.usecase.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.exception.SizeNotFoundException
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.pizza.UpdateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult
import br.com.hellopizza.api.core.usecase.pizza.dto.UpdateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.summaryDTO
import br.com.hellopizza.api.core.usecase.pizza.rule.UpdateSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.ValidateRuleExecutor
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateSizeUseCaseImpl(
    private val sizeGateway: SizeGateway,
    private val rules: List<UpdateSizeValidationRule>,
    private val executor: ValidateRuleExecutor,
) : UpdateSizeUseCase {
    override suspend fun execute(command: UpdateSizeCommand): SizeResult {
        val newSize = Size(
            id = command.id,
            description = command.description,
            toppingLimit = command.toppingLimit,
            defaultPrice = command.defaultPrice
        )
        var currentState = sizeGateway.findById(command.id)
            .orElseThrow { SizeNotFoundException(command.id) }
        val validationResult = executor.validate(rules, Optional.of(newSize), Optional.of(currentState))
        if (validationResult.valid) {
            // Operations that have violations should not be saved in the internal state of the application.
            currentState = sizeGateway.save(newSize)
        }
        return SizeResult.of(
            currentState
                .summaryDTO(), validationResult.violations
        )

    }
}