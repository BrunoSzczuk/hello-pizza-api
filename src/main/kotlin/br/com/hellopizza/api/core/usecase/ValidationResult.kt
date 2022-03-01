package br.com.hellopizza.api.core.usecase

import java.util.*

class ValidationResult private constructor(val valid: Boolean, val violations: List<String>) {
    companion object {
        fun of(violations: List<String>): ValidationResult {
            return ValidationResult(violations.isEmpty(), violations)
        }

        fun validOrWithError(valid: Boolean, errors: String): ValidationResult {
            return if (valid) ok() else error(errors)
        }

        fun ok(): ValidationResult {
            return ValidationResult(true, emptyList())
        }

        fun error(violation: String): ValidationResult {
            return errors(listOf(violation))
        }

        fun errors(violations: List<String>): ValidationResult {
            return ValidationResult(false, violations)
        }
    }
}