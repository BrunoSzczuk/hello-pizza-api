package br.com.hellopizza.api.dataprovider.base


interface BaseEntityConverter<T, E> {
    fun convertToEntity(domain: T): E
    fun convertFromEntity(entity: E): T
}