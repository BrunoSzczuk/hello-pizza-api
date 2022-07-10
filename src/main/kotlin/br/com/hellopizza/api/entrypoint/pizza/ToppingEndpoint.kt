package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.usecase.pizza.dto.CreateToppingCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.ToppingResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/toppings")
class ToppingEndpoint {
    @Autowired
    lateinit var controller: ToppingController

    @PostMapping
    suspend fun create(@RequestBody @Valid createToppingCommand: CreateToppingCommand): ResponseEntity<ToppingResult> {
        val createdTopping = controller.create(createToppingCommand)
        return ResponseEntity.created(
            UriComponentsBuilder.fromPath("/v1/toppings/{id}")
                .buildAndExpand(createdTopping.toppingSummaryDTO?.id)
                .toUri()
        )
            .body(createdTopping)
    }

    @GetMapping
    suspend fun list(@RequestParam(name = "showDisabled", defaultValue = "false") showDisabled: Boolean) =
        ResponseEntity.ok(controller.findAll(showDisabled))


    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: UUID) = ResponseEntity.ok(controller.findById(id))

}