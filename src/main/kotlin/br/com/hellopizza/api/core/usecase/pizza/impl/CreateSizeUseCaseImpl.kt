package br.com.hellopizza.api.core.usecase.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult
import br.com.hellopizza.api.core.usecase.pizza.dto.summaryDTO
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.ValidateRuleExecutor
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateSizeUseCaseImpl(
    private val sizeGateway: SizeGateway,
    private val rules: List<CreateSizeValidationRule>,
    private val executor: ValidateRuleExecutor,
) : CreateSizeUseCase {
    override suspend fun execute(command: CreateSizeCommand): SizeResult {
        val newSize = Size(
            description = command.description,
            toppingLimit = command.toppingLimit,
            defaultPrice = command.defaultPrice,
            enabled = command.enabled,
        )
        var currentState = sizeGateway.findByDescriptionAndToppingLimitAndDefaultPrice(
            command.description,
            command.toppingLimit,
            command.defaultPrice
        )

        val validationResult = executor.validate(rules, Optional.of(newSize), currentState)
        if (validationResult.valid) {
            // Operations that have violations should not be saved in the internal state of the application.
            currentState = Optional.of(sizeGateway.save(newSize))
        }
        return SizeResult.of(
            currentState.get()
                .summaryDTO(), validationResult.violations
        )

    }

}