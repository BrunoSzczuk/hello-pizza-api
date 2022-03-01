package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Configuration
class SizeRouter {
    @Bean("routerSize")
    fun route(sizeController: SizeController): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            RequestPredicates.POST("/v1/sizes").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
        ) { request: ServerRequest ->
            ServerResponse.created(URI("x1")).body(sizeController.create(request.body(BodyExtractors.toMono(CreateSizeCommand::class.java)).block()!!))
        }
    }


}