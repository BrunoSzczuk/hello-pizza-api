package br.com.hellopizza.api.dataprovider.pizza

import br.com.hellopizza.api.dataprovider.pizza.model.SizeEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigDecimal
@Repository
interface SizeRepository : ReactiveCrudRepository<SizeEntity, Long> {
    fun findByDescriptionAndToppingLimitAndDefaultPrice(description: String, toppingLimit: Long, defaultPrice: BigDecimal): Mono<SizeEntity>
}