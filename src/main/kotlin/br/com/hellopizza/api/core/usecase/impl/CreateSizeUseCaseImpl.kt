package br.com.hellopizza.api.core.usecase.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeSumarryDTO
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.SizeValidateRuleExecutor
import br.com.hellopizza.api.dataprovider.pizza.converter.SizeEntityConverter
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import javax.validation.Valid

@Service
class CreateSizeUseCaseImpl(
    private val sizeGateway: SizeGateway,
    private val rules: List<CreateSizeValidationRule>,
    private val executor: SizeValidateRuleExecutor

) : CreateSizeUseCase {
    override fun execute(@Valid command: CreateSizeCommand): Mono<SizeResult> {
        val newSize = Size(null, description = command.description, toppingLimit = command.toppingLimit, defaultPrice = command.defaultPrice)
        var currentState = sizeGateway.findByDescriptionAndToppingLimitAndDefaultPrice(command.description, command.toppingLimit, command.defaultPrice)
        val validationResult = executor.validate(rules, Mono.just(newSize), currentState)
        if (validationResult.valid) {
            // Operations that have violations should not be saved in the internal state of the application.
            currentState = sizeGateway.save(newSize)
        }
        return Mono.just(SizeResult.of(SizeSumarryDTO.from(currentState), validationResult.violations))
    }
}