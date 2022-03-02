package br.com.hellopizza.api.entrypoint.pizza

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class SizeRouter {
    @Bean("routerSize")
    fun route(sizeController: SizeController): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            RequestPredicates.POST("/v1/sizes").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
        ) { request: ServerRequest ->
            sizeController.create(request.bodyToMono())
                .flatMap { size ->
                    ServerResponse.created(
                        request.uriBuilder().path("/${size.sizeSumarryDTO.id}").build()
                    ).bodyValue(size)
                }

        }
    }


}