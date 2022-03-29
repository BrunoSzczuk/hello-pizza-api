package br.com.hellopizza.api.core.domain.customer

import java.io.Serializable

data class Customer(
    val id: Long?,
    val name: String?
) : Serializable
