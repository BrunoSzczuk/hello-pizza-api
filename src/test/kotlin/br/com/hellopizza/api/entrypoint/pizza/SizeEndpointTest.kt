package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.domain.pizza.largeSize
import br.com.hellopizza.api.core.domain.pizza.smallSize
import br.com.hellopizza.api.core.exception.SizeNotFoundException
import br.com.hellopizza.api.core.usecase.pizza.dto.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.never
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

private const val BASE_PATH = "/v1/sizes"

@WebFluxTest(SizeEndpoint::class)
@ExperimentalCoroutinesApi
internal class SizeEndpointTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var controller: SizeController


    @ParameterizedTest(name = "Post Size {0} Successfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should return HTTP 201 and the new size location `(size: Size) = runTest {
        val sizeResult = SizeResult.of(size.summaryDTO(), emptyList())
        val expectedLocation = "$BASE_PATH/${size.id}"
        val command = size.createSizeCommand()
        `when`(controller.create(eq(command))).thenReturn(sizeResult)
        val jsonContent = ObjectMapper().writeValueAsString(sizeResult)


        webTestClient.post()
            .uri(BASE_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(command), CreateSizeCommand::class.java)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().location(expectedLocation)
            .expectBody().json(jsonContent)

        verify(controller).create(eq(command))
    }

    @Test
    fun `should return HTTP 400 with a blank description`() = runTest {
        val size = Size(description = "", id = null)
        val command = size.createSizeCommand()

        webTestClient.post()
            .uri(BASE_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(command), CreateSizeCommand::class.java)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody()
            .json("""{"message":"Validation failed."}""")
        verify(controller, never()).create(eq(command))
    }

    @ParameterizedTest(name = "Put Size {0} Successfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should return HTTP 200 when a size is updated`(size: Size) = runTest {
        val sizeResult = SizeResult.of(size.summaryDTO(), emptyList())
        val command = size.updateSizeCommand()
        `when`(controller.update(eq(command))).thenReturn(sizeResult)
        val jsonContent = ObjectMapper().writeValueAsString(sizeResult)


        webTestClient.put()
            .uri(BASE_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(command), UpdateSizeCommand::class.java)
            .exchange()
            .expectStatus().isOk
            .expectBody().json(jsonContent)

        verify(controller).update(eq(command))
    }

    @Test
    fun `should return HTTP 404 when try to update an nonexistent size`() = runTest {
        val size = smallSize()
        val command = size.updateSizeCommand()
        `when`(controller.update(eq(command))).thenThrow(SizeNotFoundException(command.id))
        webTestClient.put()
            .uri(BASE_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(command), UpdateSizeCommand::class.java)
            .exchange()
            .expectStatus().isNotFound
            .expectBody()
            .json("""{"message":"${SizeNotFoundException(command.id).message}"}""")
        verify(controller).update(eq(command))
    }


    @ParameterizedTest(name = "Delete Size {0} Successfully.")
    @MethodSource("br.com.hellopizza.api.core.domain.pizza.SizeFactoriesKt#defaultSizes")
    fun `should return HTTP 204 when a size is deleted`(size: Size) = runTest {
        val command = size.deleteSizeCommand()
        webTestClient.delete()
            .uri("${BASE_PATH}/${command.id}")
            .exchange()
            .expectStatus().isOk
            .expectBody().json("{}")
        verify(controller).delete(eq(command.id))
    }

    @Test
    fun `should return HTTP 404 when try to delete an nonexistent size`() = runTest {
        val command = DeleteSizeCommand(id = 1L)
        `when`(controller.delete(eq(command.id))).thenThrow(SizeNotFoundException(command.id))
        webTestClient.delete()
            .uri("${BASE_PATH}/${command.id}")
            .exchange()
            .expectStatus().isNotFound
            .expectBody()
            .json("""{"message":"${SizeNotFoundException(command.id).message}"}""")
        verify(controller).delete(eq(command.id))
    }

    @Test
    fun `should return HTTP 404 when try to find an nonexistent size`() = runTest {
        val size = largeSize()
        val id = size.id!!
        `when`(controller.findById(eq(id))).thenThrow(SizeNotFoundException(id))
        webTestClient.get()
            .uri("${BASE_PATH}/${size.id}")
            .exchange()
            .expectStatus().isNotFound
            .expectBody()
            .json("""{"message":"${SizeNotFoundException(id).message}"}""")
        verify(controller).findById(eq(id))
    }

    @Test
    fun `should return HTTP 200 when a size is found`() = runTest {
        val size = largeSize()
        val id = size.id!!
        val sumarryDto = size.summaryDTO()
        `when`(controller.findById(eq(id))).thenReturn(size)
        val jsonContent = ObjectMapper().writeValueAsString(sumarryDto)
        webTestClient.get()
            .uri("${BASE_PATH}/${size.id}")
            .exchange()
            .expectStatus().isOk
            .expectBody().json(jsonContent)
        verify(controller).findById(eq(id))
    }
}