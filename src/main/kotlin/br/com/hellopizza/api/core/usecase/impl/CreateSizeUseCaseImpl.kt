package br.com.hellopizza.api.core.usecase.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeSumarryDTO
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.SizeValidateRuleExecutor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Service
class CreateSizeUseCaseImpl(
    private val sizeGateway: SizeGateway,
    private val rules: List<CreateSizeValidationRule>,
    private val executor: SizeValidateRuleExecutor

) : CreateSizeUseCase {
    override fun execute(@Valid command: Mono<CreateSizeCommand>): Mono<SizeResult> {
        return command.transform {
            val newSize = command.map { Size(null, description = it.description, toppingLimit = it.toppingLimit, defaultPrice = it.defaultPrice) }
            var currentState = command.flatMap { sizeGateway.findByDescriptionAndToppingLimitAndDefaultPrice(it.description, it.toppingLimit, it.defaultPrice) }
            val validationResult = executor.validate(rules, newSize, currentState)
            if (validationResult.valid) {
                // Operations that have violations should not be saved in the internal state of the application.
                currentState = newSize.flatMap { sizeGateway.save(it) }
            }
            Mono.just(SizeResult.of(SizeSumarryDTO.from(currentState), validationResult.violations))
        }
    }
}