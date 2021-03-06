package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.usecase.pizza.dto.*
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/sizes")
class SizeEndpoint {
    @Autowired
    lateinit var sizeController: SizeController

    @PostMapping
    suspend fun create(@RequestBody @Valid createSizeCommand: CreateSizeCommand): ResponseEntity<SizeResult> {
        val createdSize = sizeController.create(createSizeCommand)
        return ResponseEntity.created(
            UriComponentsBuilder.fromPath("/v1/sizes/{id}")
                .buildAndExpand(createdSize.sizeSumarryDTO?.id)
                .toUri()
        )
            .body(createdSize)
    }

    @PutMapping
    suspend fun update(@RequestBody @Valid update: UpdateSizeCommand) =
        ResponseEntity.ok(sizeController.update(update))


    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: UUID) = ResponseEntity.ok(
        sizeController.findById(id)
            .sizeDTO()
    )

    @GetMapping
    suspend fun findAll(@RequestParam(value = "showDisabled", defaultValue = "false") showDisabled: Boolean) =
        ResponseEntity.ok(
            sizeController.findAll(showDisabled).map { it.summaryDTO() }
        )

    @DeleteMapping("/{id}")
    suspend fun deleteById(@PathVariable id: UUID) = ResponseEntity.ok(sizeController.delete(id))

}