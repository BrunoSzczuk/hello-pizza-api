package br.com.hellopizza.api.core.usecase.pizza.dto

class ToppingResult(
    val toppingSummaryDTO: ToppingSummaryDTO?,
    val violations: Collection<String>
) {

    companion object {
        fun of(
            toppingSummaryDTO: ToppingSummaryDTO?,
            violations: Collection<String>
        ): ToppingResult {
            return ToppingResult(toppingSummaryDTO, violations)
        }
    }
}


