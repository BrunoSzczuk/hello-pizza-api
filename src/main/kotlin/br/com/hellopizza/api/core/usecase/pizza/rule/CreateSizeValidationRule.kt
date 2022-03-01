package br.com.hellopizza.api.core.usecase.pizza.rule

import br.com.hellopizza.api.core.domain.pizza.Size
import reactor.core.publisher.Mono

interface CreateSizeValidationRule : SizeValidateRule<Mono<Size>> {
}