package br.com.hellopizza.api.core.usecase.pizza.rule

import br.com.hellopizza.api.core.usecase.ValidationResult
import org.springframework.core.Ordered
import java.util.*

interface SizeValidateRule<T> : Ordered {
    suspend fun validate(modification: T, currentSizeState: Optional<T>): ValidationResult
    override fun getOrder(): Int {
        return Ordered.LOWEST_PRECEDENCE
    }

    fun breakWithError(): Boolean {
        return false
    }
}