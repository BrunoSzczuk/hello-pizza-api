package br.com.hellopizza.api.core.usecase.pizza.dto

class SizeResult(val sizeSumarryDTO: SizeSumarryDTO, val violations: Collection<String>) {

    companion object {
        fun of(sizeSumarryDTO: SizeSumarryDTO, violations: Collection<String>): SizeResult {
            return SizeResult(sizeSumarryDTO, violations)
        }
    }
}


