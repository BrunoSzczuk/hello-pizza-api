package br.com.hellopizza.api.core.usecase.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Topping
import br.com.hellopizza.api.core.gateway.pizza.ToppingGateway
import br.com.hellopizza.api.core.usecase.pizza.CreateToppingUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateToppingCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.ToppingResult
import br.com.hellopizza.api.core.usecase.pizza.dto.summaryDTO
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateToppingValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.ValidateRuleExecutor
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateToppingUseCaseImpl(
    private val gateway: ToppingGateway,
    private val rules: List<CreateToppingValidationRule>,
    private val executor: ValidateRuleExecutor,
) : CreateToppingUseCase {

    override suspend fun execute(command: CreateToppingCommand): ToppingResult {
        val newTopping = Topping(
            description = command.description,
            additionalPrice = command.additionalPrice,
            enabled = command.enabled,
        )
        var currentState = gateway.findByDescriptionAndAdditionalPrice(
            command.description,
            command.additionalPrice
        )

        val validationResult = executor.validate(rules, Optional.of(newTopping), currentState)
        if (validationResult.valid) {
            // Operations that have violations should not be saved in the internal state of the application.
            currentState = Optional.of(gateway.save(newTopping))
        }
        return ToppingResult.of(
            currentState.get()
                .summaryDTO(), validationResult.violations
        )
    }
}