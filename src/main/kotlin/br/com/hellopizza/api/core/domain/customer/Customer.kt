package br.com.hellopizza.api.core.domain.customer

import org.springframework.data.annotation.Id
import java.io.Serializable

data class Customer(
    @Id
    val id: IdCustomer,
    val name: String?
) : Serializable
