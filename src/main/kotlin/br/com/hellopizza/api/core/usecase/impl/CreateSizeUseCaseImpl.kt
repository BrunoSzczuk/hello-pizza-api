package br.com.hellopizza.api.core.usecase.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult
import br.com.hellopizza.api.core.usecase.pizza.dto.converter.SizeDTOConverter
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.SizeValidateRuleExecutor
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import javax.validation.Valid

@Service
class CreateSizeUseCaseImpl(
    private val sizeGateway: SizeGateway,
    private val rules: List<CreateSizeValidationRule>,
    private val executor: SizeValidateRuleExecutor
) : CreateSizeUseCase {
    override suspend fun execute(@Valid command: CreateSizeCommand): SizeResult {
        val converter: SizeDTOConverter = Mappers.getMapper(SizeDTOConverter::class.java)
        val newSize = Size(null, description = command.description, toppingLimit = command.toppingLimit, defaultPrice = command.defaultPrice)
        var currentState = sizeGateway.findByDescriptionAndToppingLimitAndDefaultPrice(command.description, command.toppingLimit, command.defaultPrice)

        val validationResult = executor.validate(rules, newSize, currentState)
        if (validationResult.valid) {
            // Operations that have violations should not be saved in the internal state of the application.
            currentState = Optional.of(sizeGateway.save(newSize))
        }
        return SizeResult.of(converter.convertToDTO(currentState.get()), validationResult.violations)

    }

}