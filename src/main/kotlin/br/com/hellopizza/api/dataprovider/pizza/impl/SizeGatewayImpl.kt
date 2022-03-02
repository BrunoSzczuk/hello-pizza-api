package br.com.hellopizza.api.dataprovider.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.dataprovider.pizza.SizeRepository
import br.com.hellopizza.api.dataprovider.pizza.converter.SizeEntityConverter
import mu.KotlinLogging
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
class SizeGatewayImpl(private var repository: SizeRepository) : SizeGateway {
    val converter: SizeEntityConverter = Mappers.getMapper(SizeEntityConverter::class.java)
    private val logger = KotlinLogging.logger {}
    override fun findByDescriptionAndToppingLimitAndDefaultPrice(description: String, toppingLimit: Long, defaultPrice: BigDecimal): Mono<Size> {
        logger.info { "Hitting on Database. Looking for a size with {description: ${description}, toppingLimit: ${toppingLimit}, defaultPrice: ${defaultPrice}}." }
        return repository.findByDescriptionAndToppingLimitAndDefaultPrice(description, toppingLimit, defaultPrice).map { converter.convertFromEntity(it) }
    }

    override fun findById(id: Long): Mono<Size> {
        logger.info { "Hitting on Database. Looking for a size with id ${id}." }
        return repository.findById(id).map { converter.convertFromEntity(it) }
    }

    override fun findAll(): Flux<Size> {
        logger.info { "Hitting on Database. Looking for all sizes." }
        return repository.findAll().map { converter.convertFromEntity(it) }
    }

    override fun save(data: Size): Mono<Size> {
        logger.info { "Hitting on Database. Trying to save a new size." }
        val size = repository.save(converter.convertToEntity(data)).map {
            logger.info { "Save a new size successfully. Size id: ${it.id}." }
            converter.convertFromEntity(it)
        }
        return size
    }


}