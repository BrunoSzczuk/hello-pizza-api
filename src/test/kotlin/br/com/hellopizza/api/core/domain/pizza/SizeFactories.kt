package br.com.hellopizza.api.core.domain.pizza

import java.math.BigDecimal

fun smallSize(
        id: Long = SIZE_ID_SMALL,
        description: String = SIZE_DESCRIPTION_SMALL,
        toppingLimit: Long = SIZE_TOPPING_LIMIT_SMALL,
        defaultPrice: BigDecimal = SIZE_DEFAULT_PRICE_SMALL
) = Size(id, description, toppingLimit, defaultPrice)

fun mediumSize(
        id: Long = SIZE_ID_MEDIUM,
        description: String = SIZE_DESCRIPTION_MEDIUM,
        toppingLimit: Long = SIZE_TOPPING_LIMIT_MEDIUM,
        defaultPrice: BigDecimal = SIZE_DEFAULT_PRICE_MEDIUM
) = Size(id, description, toppingLimit, defaultPrice)

fun largeSize(
        id: Long = SIZE_ID_LARGE,
        description: String = SIZE_DESCRIPTION_LARGE,
        toppingLimit: Long = SIZE_TOPPING_LIMIT_LARGE,
        defaultPrice: BigDecimal = SIZE_DEFAULT_PRICE_LARGE
) = Size(id, description, toppingLimit, defaultPrice)

fun defaultSizes() = listOf<Size>(smallSize(), mediumSize(), largeSize())