package br.com.hellopizza.api.core.usecase.pizza.dto

import br.com.hellopizza.api.core.domain.pizza.Size

fun Size.createSizeCommand() =
    CreateSizeCommand(description = description, toppingLimit = toppingLimit, defaultPrice = defaultPrice)

fun Size.updateSizeCommand() =
    UpdateSizeCommand(id = id, description = description, toppingLimit = toppingLimit, defaultPrice = defaultPrice)

fun Size.deleteSizeCommand() =
    DeleteSizeCommand(id = id)

fun Size.summaryDTO() = SizeSumarryDTO(id = id, description = description, defaultPrice = defaultPrice)
fun Size.prepareForCreate() =
    Size(description = description, toppingLimit = toppingLimit, defaultPrice = defaultPrice)

