package br.com.hellopizza.api.core.usecase


interface BaseDTOConverter<T, E> {
    fun convertToDTO(domain: T): E
    fun convertFromDTO(dto: E): T
}