package br.com.hellopizza.api.core.usecase.pizza.dto

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.domain.pizza.Topping

fun Size.createSizeCommand() =
    CreateSizeCommand(description = description, toppingLimit = toppingLimit, defaultPrice = defaultPrice)

fun Size.updateSizeCommand() =
    UpdateSizeCommand(id = id, description = description, toppingLimit = toppingLimit, defaultPrice = defaultPrice)

fun Size.deleteSizeCommand() =
    DeleteSizeCommand(id = id)

fun Size.summaryDTO() =
    SizeSumarryDTO(id = id, description = description, defaultPrice = defaultPrice, enabled = enabled)

fun Size.sizeDTO() = SizeDTO(
    id = id,
    description = description,
    defaultPrice = defaultPrice,
    enabled = enabled,
    toppingLimit = toppingLimit
)

fun Size.prepareForCreate() =
    Size(description = description, toppingLimit = toppingLimit, defaultPrice = defaultPrice, enabled = enabled)

fun Topping.summaryDTO() =
    ToppingSummaryDTO(id = id, description = description, additionalPrice = additionalPrice, enabled = enabled)

fun Topping.toppingDTO() =
    ToppingDTO(id = id, description = description, additionalPrice = additionalPrice, enabled = enabled)