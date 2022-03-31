package br.com.hellopizza.api.core.domain.pizza

import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeSumarryDTO

fun Size.createSizeCommand() = CreateSizeCommand(description = description, toppingLimit = toppingLimit, defaultPrice = defaultPrice)
fun Size.summaryDTO() = SizeSumarryDTO(id = id!!, description = description, defaultPrice = defaultPrice)
fun Size.prepareForCreate() = Size(id = null, description = description, toppingLimit = toppingLimit, defaultPrice = defaultPrice)
