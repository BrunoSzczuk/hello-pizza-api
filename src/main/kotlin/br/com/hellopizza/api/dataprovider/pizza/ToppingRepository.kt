package br.com.hellopizza.api.dataprovider.pizza

import br.com.hellopizza.api.dataprovider.pizza.model.IdToppingEntity
import br.com.hellopizza.api.dataprovider.pizza.model.ToppingEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ToppingRepository : ReactiveCrudRepository<ToppingEntity, Long> {
}