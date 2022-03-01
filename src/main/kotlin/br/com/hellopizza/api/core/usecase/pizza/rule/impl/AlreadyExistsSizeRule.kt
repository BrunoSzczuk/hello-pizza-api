package br.com.hellopizza.api.core.usecase.pizza.rule.impl

import br.com.hellopizza.api.core.config.ApplicationCoreProperties
import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.usecase.ValidationResult
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateSizeValidationRule
import reactor.core.publisher.Mono
import java.util.*

class AlreadyExistsSizeRule(private val applicationCoreProperties: ApplicationCoreProperties) : CreateSizeValidationRule {
    //RULE: Once created, the size must not be updated or recreated.
    override fun validate(modification: Mono<Size>, currentSizeState: Mono<Size>): ValidationResult {
        val valid = Objects.isNull(currentSizeState)
        return ValidationResult.validOrWithError(valid, applicationCoreProperties.error.sizeAlreadyExistsKey!!)
    }
}