package br.com.hellopizza.api.core.gateway.pizza

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.BasicGateway
import reactor.core.publisher.Mono
import java.math.BigDecimal

interface SizeGateway : BasicGateway<Size, Long> {
    fun findByDescriptionAndToppingLimitAndDefaultPrice(description: String, toppingLimit : Long, defaultPrice: BigDecimal) : Mono<Size>
}