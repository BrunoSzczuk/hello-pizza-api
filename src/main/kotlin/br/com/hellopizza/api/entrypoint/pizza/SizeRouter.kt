package br.com.hellopizza.api.entrypoint.pizza

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Configuration
class SizeRouter {
    @Bean("routerSize")
    fun route(sizeController: SizeController): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            RequestPredicates.POST("/v1/sizes").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
        ) { request: ServerRequest ->
            ServerResponse.created(URI("x1")).body(sizeController.create(request.bodyToMono()))
        }
    }


}