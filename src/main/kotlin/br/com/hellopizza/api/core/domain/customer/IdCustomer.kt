package br.com.hellopizza.api.core.domain.customer

import java.io.Serializable
import java.util.*

data class IdCustomer(var id: UUID = UUID.randomUUID()) : Serializable
