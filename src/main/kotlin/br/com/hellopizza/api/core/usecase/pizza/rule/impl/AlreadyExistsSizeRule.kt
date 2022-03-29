package br.com.hellopizza.api.core.usecase.pizza.rule.impl

import br.com.hellopizza.api.core.config.ApplicationCoreProperties
import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.usecase.ValidationResult
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateSizeValidationRule
import org.springframework.stereotype.Component
import java.util.*

@Component
class AlreadyExistsSizeRule(private val applicationCoreProperties: ApplicationCoreProperties) : CreateSizeValidationRule {
    //RULE: Once created, the size must not be updated or recreated.
    override suspend fun validate(modification: Size, currentSizeState: Optional<Size>): ValidationResult {
        val valid = currentSizeState.isEmpty
        return ValidationResult.validOrWithError(valid, applicationCoreProperties.error.sizeAlreadyExistsKey!!)
    }

}