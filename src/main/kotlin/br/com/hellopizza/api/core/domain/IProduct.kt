package br.com.hellopizza.api.core.domain

import java.math.BigDecimal

interface IProduct {
    fun total(): BigDecimal
    fun observation(): String
}