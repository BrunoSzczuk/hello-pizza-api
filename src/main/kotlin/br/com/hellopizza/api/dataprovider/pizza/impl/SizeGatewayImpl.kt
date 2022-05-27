package br.com.hellopizza.api.dataprovider.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.dataprovider.pizza.SizeRepository
import br.com.hellopizza.api.dataprovider.pizza.converter.SizeEntityConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mu.KotlinLogging
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class SizeGatewayImpl(private var repository: SizeRepository) : SizeGateway {
    val converter: SizeEntityConverter = Mappers.getMapper(SizeEntityConverter::class.java)
    private val logger = KotlinLogging.logger {}
    override suspend fun findByDescriptionAndToppingLimitAndDefaultPrice(description: String,
                                                                         toppingLimit: Long,
                                                                         defaultPrice: BigDecimal): Optional<Size> {
        logger.info { "Hitting on Database. Looking for a size with {description: ${description}, toppingLimit: ${toppingLimit}, defaultPrice: ${defaultPrice}}." }
        return Optional.ofNullable(repository.findByDescriptionAndToppingLimitAndDefaultPrice(description, toppingLimit, defaultPrice))
                .map { converter.convertFromEntity(it) }
    }

    override suspend fun findById(id: UUID): Optional<Size> {
        logger.info { "Hitting on Database. Looking for a size with id ${id}." }
        return Optional.ofNullable(repository.findById(id))
            .map { converter.convertFromEntity(it) }
    }

    override suspend fun deleteById(id: UUID) {
        logger.info { "Hitting on Database. Deleting a size with id ${id}." }
        repository.deleteById(id)
    }

    override fun findAll(): Flow<Size> {
        logger.info { "Hitting on Database. Looking for all sizes." }
        return repository.findAll()
                .map { converter.convertFromEntity(it) }
    }

    override suspend fun save(data: Size): Size {
        logger.info { "Hitting on Database. Trying to save a size." }
        return converter.convertFromEntity(repository.save(converter.convertToEntity(data)))

    }


}