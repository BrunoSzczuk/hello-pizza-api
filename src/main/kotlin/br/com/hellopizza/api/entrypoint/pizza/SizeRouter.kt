package br.com.hellopizza.api.entrypoint.pizza

import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@RestController
class SizeRouter {
    @Bean("routerSize")
    fun route(sizeController: SizeController) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/v1/sizes") {
                val result = sizeController.create(it.awaitBody())
                ServerResponse.created(
                    it.uriBuilder().path("/${result.sizeSumarryDTO?.id}").build()
                ).bodyValueAndAwait(result)
            }
        }

    }
}