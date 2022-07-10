package br.com.hellopizza.api.core.exception

import java.util.*

sealed class NotFoundException(message: String) : RuntimeException(message)
class SizeNotFoundException(id: UUID) : NotFoundException("Size with id $id not found.")
class ToppingNotFoundException(id: UUID) : NotFoundException("Topping with id $id not found.")
