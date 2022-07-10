package br.com.hellopizza.api.dataprovider.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Topping
import br.com.hellopizza.api.core.gateway.pizza.ToppingGateway
import br.com.hellopizza.api.dataprovider.pizza.ToppingRepository
import br.com.hellopizza.api.dataprovider.pizza.converter.ToppingEntityConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mu.KotlinLogging
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class ToppingGatewayImpl(private var repository: ToppingRepository) : ToppingGateway {
    val converter: ToppingEntityConverter = Mappers.getMapper(ToppingEntityConverter::class.java)
    private val logger = KotlinLogging.logger {}
    override suspend fun findByDescriptionAndAdditionalPrice(
        description: String,
        additionalPrice: BigDecimal
    ): Optional<Topping> {
        return Optional.ofNullable(repository.findByDescriptionAndAdditionalPrice(description, additionalPrice)).map {
            converter.convertFromEntity(it)
        }
    }

    override suspend fun findById(id: UUID): Optional<Topping> {
        logger.info { "Hitting on Database. Looking for a topping with id ${id}." }
        return Optional.ofNullable(repository.findById(id))
            .map { converter.convertFromEntity(it) }
    }

    override suspend fun deleteById(id: UUID) {
        logger.info { "Hitting on Database. Deleting a topping with id ${id}." }
        repository.deleteById(id)
    }

    override fun findAll(showDisabled: Boolean): Flow<Topping> {
        logger.info { "Hitting on Database. Looking for all toppings." }
        return repository.findAllByEnabledIsTrueOrEnabledIs(!showDisabled)
            .map { converter.convertFromEntity(it) }
    }

    override suspend fun save(data: Topping): Topping {
        logger.info { "Hitting on Database. Trying to save a topping." }
        return converter.convertFromEntity(repository.save(converter.convertToEntity(data)))

    }


}