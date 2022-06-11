package br.com.hellopizza.api.core.usecase.pizza.impl

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.exception.NotFoundException
import br.com.hellopizza.api.core.gateway.pizza.SizeGateway
import br.com.hellopizza.api.core.usecase.ValidationResult
import br.com.hellopizza.api.core.usecase.pizza.dto.deleteSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.rule.DeleteSizeValidationRule
import br.com.hellopizza.api.core.usecase.pizza.rule.ValidateRuleExecutor
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import java.util.*

internal class DeleteSizeUseCaseImplTest {
    private var sizeGateway = mock<SizeGateway>()
    private var rules = mock<List<DeleteSizeValidationRule>>()
    private var executor = mock<ValidateRuleExecutor>()
    private var deleteSizeUseCaseImpl = DeleteSizeUseCaseImpl(sizeGateway, rules, executor)


    @ExperimentalCoroutinesApi
    @ParameterizedTest(name = "Delete Size {0} Successfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should delete size successfully`(size: Size) = runTest {
        //Given
        Mockito.`when`(
            sizeGateway.findById(any())
        )
            .thenReturn(Optional.of(size))
        Mockito.`when`(executor.validate(rules, Optional.empty(), Optional.of(size)))
            .thenReturn(ValidationResult.ok())
        Mockito.`when`(sizeGateway.save(size)).thenReturn(size)

        //When
        deleteSizeUseCaseImpl.execute(size.deleteSizeCommand())

        //Then
        verify(sizeGateway).deleteById(size.id)
    }

    @ExperimentalCoroutinesApi
    @ParameterizedTest(name = "Delete Size {0} Unsuccessfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should not delete a Size because it is not valid`(size: Size) = runTest {
        //Given
        Mockito.`when`(
            sizeGateway.findById(any())
        )
            .thenReturn(Optional.empty())

        //When
        assertThrows<NotFoundException> { deleteSizeUseCaseImpl.execute(size.deleteSizeCommand()) }

        //Then
        verify(executor, never()).validate(eq(rules), any(), any())
        verify(sizeGateway, never()).save(any())

    }
}