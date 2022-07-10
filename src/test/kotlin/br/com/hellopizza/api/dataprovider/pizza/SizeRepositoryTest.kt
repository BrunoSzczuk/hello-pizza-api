package br.com.hellopizza.api.dataprovider.pizza

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.domain.pizza.defaultSizes
import br.com.hellopizza.api.dataprovider.pizza.converter.SizeEntityConverter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest

@DataR2dbcTest
@ExperimentalCoroutinesApi
@TestInstance(Lifecycle.PER_CLASS)
@Disabled
internal class SizeRepositoryTest {
    @Autowired
    lateinit var repository: SizeRepository


    private val sizeEntityConverter: SizeEntityConverter = Mappers.getMapper(SizeEntityConverter::class.java)

    @BeforeAll
    internal fun setUp() = runTest {
        repository.saveAll(defaultSizes().map { sizeEntityConverter.convertToEntity(it) }).onCompletion { }
    }

    @AfterEach
    internal fun tearDown() = runTest {
        repository.deleteAll()
    }

    @ParameterizedTest(name = "should return the size {0} from the database")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")

    fun `sould find a Size with given description and topping limit and default price`(size: Size) = runTest {
        val sizes = repository.findByDescriptionAndToppingLimitAndDefaultPrice(
            size.description,
            size.toppingLimit,
            size.defaultPrice
        )
        assertNotNull(sizes)
    }
}