package br.com.hellopizza.api.core.usecase.pizza.rule

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.usecase.ValidationResult
import org.springframework.core.Ordered

interface SizeValidateRule<T> : Ordered {
    fun validate(modification: T, currentSizeState: Size?): ValidationResult
    override fun getOrder(): Int {
        return Ordered.LOWEST_PRECEDENCE
    }

    fun breakWithError(): Boolean {
        return false
    }
}