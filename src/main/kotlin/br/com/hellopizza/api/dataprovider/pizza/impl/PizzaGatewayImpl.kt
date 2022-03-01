package br.com.hellopizza.api.dataprovider.pizza.impl

import br.com.hellopizza.api.core.gateway.pizza.PizzaGateway
import br.com.hellopizza.api.dataprovider.pizza.PizzaRepository
import br.com.hellopizza.api.dataprovider.pizza.model.PizzaEntity
import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class PizzaGatewayImpl(private var repository: PizzaRepository) : PizzaGateway {
    private val logger = KotlinLogging.logger {}

    override fun findById(id: Long): Mono<PizzaEntity> {
        logger.info { "Hitting on Database. Looking for a pizza with id ${id}." }
        return repository.findById(id);
    }

    override fun findAll(): Flux<PizzaEntity> {
        logger.info { "Hitting on Database. Looking for all pizzas." }
        return repository.findAll();
    }

    override fun save(data: PizzaEntity): Mono<PizzaEntity> {
        logger.info { "Hitting on Database. Trying to save a new pizza." }
        return repository.save(data);
    }
}