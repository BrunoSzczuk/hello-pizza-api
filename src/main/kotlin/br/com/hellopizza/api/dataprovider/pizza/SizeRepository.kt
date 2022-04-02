package br.com.hellopizza.api.dataprovider.pizza

import br.com.hellopizza.api.dataprovider.pizza.model.SizeEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface SizeRepository : CoroutineCrudRepository<SizeEntity, Long> {
    suspend fun findByDescriptionAndToppingLimitAndDefaultPrice(description: String,
                                                                toppingLimit: Long,
                                                                defaultPrice: BigDecimal): SizeEntity
}