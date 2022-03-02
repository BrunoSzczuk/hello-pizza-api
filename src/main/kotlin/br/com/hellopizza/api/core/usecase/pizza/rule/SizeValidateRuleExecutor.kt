package br.com.hellopizza.api.core.usecase.pizza.rule

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.usecase.ValidationResult
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

@Component
class SizeValidateRuleExecutor {
    fun <T, R : SizeValidateRule<T>> validate(rules: List<R>, modification: T, currentSizeState: Mono<Size>): ValidationResult {
        val violations: MutableList<String> = LinkedList()
        for (rule in rules) {
            val validationResult = rule.validate(modification, currentSizeState)
            violations.addAll(validationResult.violations)
            // if it's not valid and must break the chain execution in this case
            if (!validationResult.valid && rule.breakWithError()) {
                break
            }
        }
        return ValidationResult.of(violations)
    }
}