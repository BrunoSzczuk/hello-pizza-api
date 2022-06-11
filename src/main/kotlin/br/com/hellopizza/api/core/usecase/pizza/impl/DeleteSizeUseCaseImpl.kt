package br.com.hellopizza.api.core.usecase.pizza.impl

import br.com.hellopizza.api.core.exception.SizeNotFoundException
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.pizza.DeleteSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.DeleteSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.rule.DeleteSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.ValidateRuleExecutor
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeleteSizeUseCaseImpl(
    private val sizeGateway: SizeGateway,
    private val rules: List<DeleteSizeValidationRule>,
    private val executor: ValidateRuleExecutor,
) : DeleteSizeUseCase {
    override suspend fun execute(command: DeleteSizeCommand) {
        val currentState = sizeGateway.findById(command.id)
            .orElseThrow { SizeNotFoundException(command.id) }
        val validationResult = executor.validate(rules, Optional.empty(), Optional.of(currentState))
        if (validationResult.valid) {
            sizeGateway.deleteById(command.id)
        }
    }
}