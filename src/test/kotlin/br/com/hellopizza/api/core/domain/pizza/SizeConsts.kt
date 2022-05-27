package br.com.hellopizza.api.core.domain.pizza

import java.math.BigDecimal
import java.util.*

val SIZE_ID_SMALL: UUID = UUID.fromString("d8f9c8e0-c8e0-11ea-bc55-0242ac130003")
val SIZE_ID_MEDIUM: UUID = UUID.fromString("d8f9c8e0-c8e0-11ea-bc55-0242ac130004")
val SIZE_ID_LARGE: UUID = UUID.fromString("d8f9c8e0-c8e0-11ea-bc55-0242ac130005")
const val SIZE_DESCRIPTION_SMALL = "Small Size"
const val SIZE_DESCRIPTION_MEDIUM = "Medium Size"
const val SIZE_DESCRIPTION_LARGE = "Large Size"
const val SIZE_TOPPING_LIMIT_SMALL = 2L
const val SIZE_TOPPING_LIMIT_MEDIUM = 3L
const val SIZE_TOPPING_LIMIT_LARGE = 4L
val SIZE_DEFAULT_PRICE_SMALL: BigDecimal = BigDecimal.valueOf(39.99)
val SIZE_DEFAULT_PRICE_MEDIUM: BigDecimal = BigDecimal.valueOf(54.99)
val SIZE_DEFAULT_PRICE_LARGE: BigDecimal = BigDecimal.valueOf(75.5)