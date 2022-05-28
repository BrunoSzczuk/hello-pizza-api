package br.com.hellopizza.api.core.usecase.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.exception.NotFoundException
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.ValidationResult
import br.com.hellopizza.api.core.usecase.pizza.dto.summaryDTO
import br.com.hellopizza.api.core.usecase.pizza.dto.updateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.rule.UpdateSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.ValidateRuleExecutor
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import java.util.*

internal class UpdateSizeUseCaseImplTest {

    private var sizeGateway = mock<SizeGateway>()
    private var rules = mock<List<UpdateSizeValidationRule>>()
    private var executor = mock<ValidateRuleExecutor>()
    private var updateSizeUseCaseImpl = UpdateSizeUseCaseImpl(sizeGateway, rules, executor)

    @ExperimentalCoroutinesApi
    @ParameterizedTest(name = "Update Size {0} Successfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should update and save if it is valid`(size: Size) = runTest {
        //Given
        val modifiedSize = size.copy(description = "Modified Size")
        Mockito.`when`(
            sizeGateway.findById(any())
        )
            .thenReturn(Optional.of(size))
        Mockito.`when`(executor.validate(rules, Optional.of(modifiedSize), Optional.of(size)))
            .thenReturn(ValidationResult.ok())
        Mockito.`when`(sizeGateway.save(modifiedSize)).thenReturn(modifiedSize)

        //When
        val result = updateSizeUseCaseImpl.execute(modifiedSize.updateSizeCommand())

        //Then
        Assertions.assertTrue(result.violations.isEmpty())
        Assertions.assertEquals(result.sizeSumarryDTO, modifiedSize.summaryDTO())
        verify(sizeGateway).save(modifiedSize)
    }

    @ExperimentalCoroutinesApi
    @ParameterizedTest(name = "Update Size {0} Unsuccessfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should not update a Size because it is not valid`(size: Size) = runTest {
        //Given
        val modifiedSize = size.copy(description = "Modified Size")
        Mockito.`when`(
            sizeGateway.findById(any())
        )
            .thenReturn(Optional.empty())

        //When
        assertThrows<NotFoundException> { updateSizeUseCaseImpl.execute(modifiedSize.updateSizeCommand()) }

        //Then
        verify(executor, never()).validate(eq(rules), any(), any())
        verify(sizeGateway, never()).save(any())

    }
}