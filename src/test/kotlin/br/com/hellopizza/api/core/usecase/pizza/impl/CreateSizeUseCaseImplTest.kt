package br.com.hellopizza.api.core.usecase.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.ValidationResult
import br.com.hellopizza.api.core.usecase.pizza.dto.createSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.prepareForCreate
import br.com.hellopizza.api.core.usecase.pizza.dto.summaryDTO
import br.com.hellopizza.api.core.usecase.pizza.rule.CreateSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.ValidateRuleExecutor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.never
import org.mockito.Mockito.`when`
import java.util.*

internal class CreateSizeUseCaseImplTest {
    private var sizeGateway = mock<SizeGateway>()
    private var rules = mock<List<CreateSizeValidationRule>>()
    private var executor = mock<ValidateRuleExecutor>()
    private var createSizeUseCaseImpl = CreateSizeUseCaseImpl(sizeGateway, rules, executor)

    @ExperimentalCoroutinesApi
    @ParameterizedTest(name = "Create Size {0} Successfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should create and save if it is valid`(size: Size) = runTest {
        //Given
        val preparedSize = size.prepareForCreate()
        `when`(sizeGateway.findByDescriptionAndToppingLimitAndDefaultPrice(size.description, size.toppingLimit, size.defaultPrice))
                .thenReturn(Optional.empty())
        `when`(executor.validate(rules, Optional.of(preparedSize), Optional.empty()))
                .thenReturn(ValidationResult.ok())
        `when`(sizeGateway.save(preparedSize)).thenReturn(size)

        //When
        val result = createSizeUseCaseImpl.execute(size.createSizeCommand())

        //Then
        assertTrue(result.violations.isEmpty())
        assertEquals(result.sizeSumarryDTO, size.summaryDTO())
        verify(sizeGateway).save(preparedSize)
    }

    @ExperimentalCoroutinesApi
    @ParameterizedTest(name = "Create Size {0} Unsuccessfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should not create a Size because the validation is failed`(size: Size) = runTest {
        //Given
        val preparedSize = size.prepareForCreate()
        val savedSize = Optional.of(size)
        `when`(sizeGateway.findByDescriptionAndToppingLimitAndDefaultPrice(size.description, size.toppingLimit, size.defaultPrice))
                .thenReturn(savedSize)
        `when`(executor.validate(rules, Optional.of(preparedSize), savedSize))
                .thenReturn(ValidationResult.error("error"))
        `when`(sizeGateway.save(preparedSize)).thenReturn(size)

        //When
        val result = createSizeUseCaseImpl.execute(size.createSizeCommand())

        //Then
        assertFalse(result.violations.isEmpty())
        assertEquals(result.sizeSumarryDTO, size.summaryDTO())
        verify(sizeGateway, never()).save(preparedSize)
    }

}