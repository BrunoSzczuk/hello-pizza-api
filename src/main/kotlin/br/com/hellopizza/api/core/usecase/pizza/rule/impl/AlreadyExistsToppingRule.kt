package br.com.hellopizza.api.core.usecase.pizza.rule.impl

import br.com.hellopizza.api.core.config.ApplicationCoreProperties
import br.com.hellopizza.api.core.domain.pizza.Topping
import br.com.hellopizza.api.core.usecase.ValidationResult
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateToppingValidationRule
import org.springframework.stereotype.Component
import java.util.*

@Component
class AlreadyExistsToppingRule(private val applicationCoreProperties: ApplicationCoreProperties) :
    CreateToppingValidationRule {
    //RULE: Once created, the size must not be updated or recreated.
    override suspend fun validate(
        modification: Optional<Topping>,
        currentSizeState: Optional<Topping>
    ): ValidationResult {
        val valid = currentSizeState.isEmpty
        return ValidationResult.validOrWithError(valid, applicationCoreProperties.error.toppingAlreadyExistsKey!!)
    }

}