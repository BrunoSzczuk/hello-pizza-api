package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.usecase.pizza.CreateSizeUseCase
import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
class SizeResource(private val createSizeUseCase: CreateSizeUseCase) {
    @PostMapping("/v2/sizes")
    fun register(@RequestBody @Valid command: Mono<CreateSizeCommand>): Mono<SizeResult> {
        return this.createSizeUseCase.execute(command)
    }
}