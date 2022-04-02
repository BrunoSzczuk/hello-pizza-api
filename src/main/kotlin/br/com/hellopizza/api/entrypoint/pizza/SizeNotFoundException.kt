package br.com.hellopizza.api.entrypoint.pizza

import br.com.hellopizza.api.core.exception.NotFoundException

class SizeNotFoundException(id: Long) : NotFoundException("Size with id $id not found.")
