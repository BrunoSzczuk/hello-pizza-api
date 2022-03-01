package br.com.hellopizza.api.core.usecase.pizza.dto

import reactor.core.publisher.Mono

class SizeResult(val sizeSumarryDTO: Mono<SizeSumarryDTO>, val violations: Collection<String>) {

    companion object {
        fun of(sizeSumarryDTO: Mono<SizeSumarryDTO>, violations: Collection<String>): SizeResult {
            return SizeResult(sizeSumarryDTO, violations);
        }
    }
}


