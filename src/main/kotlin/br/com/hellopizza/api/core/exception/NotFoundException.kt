package br.com.hellopizza.api.core.exception

sealed class NotFoundException(message: String) : RuntimeException(message)
class SizeNotFoundException(id: Long) : NotFoundException("Size with id $id not found.")
