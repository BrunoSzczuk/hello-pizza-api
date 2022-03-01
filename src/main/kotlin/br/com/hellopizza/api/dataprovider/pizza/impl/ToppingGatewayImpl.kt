package br.com.hellopizza.api.dataprovider.pizza.impl

import br.com.hellopizza.api.core.gateway.pizza.ToppingGateway
import br.com.hellopizza.api.dataprovider.pizza.ToppingRepository
import br.com.hellopizza.api.dataprovider.pizza.model.ToppingEntity
import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ToppingGatewayImpl(private var repository: ToppingRepository) : ToppingGateway {
    private val logger = KotlinLogging.logger {}
    override fun findById(id: Long): Mono<ToppingEntity> {
        logger.info { "Hitting on Database. Looking for a size with id ${id}." }
        return repository.findById(id);
    }

    override fun findAll(): Flux<ToppingEntity> {
        logger.info { "Hitting on Database. Looking for all toppings." }
        return repository.findAll();
    }

    override fun save(data: ToppingEntity): Mono<ToppingEntity> {
        logger.info { "Hitting on Database. Trying to save a new topping." }
        return repository.save(data);
    }

}