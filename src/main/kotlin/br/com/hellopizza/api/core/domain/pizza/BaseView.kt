package br.com.hellopizza.api.core.domain.pizza

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable

abstract class BaseView<ID>(
    private val id: ID
) : Persistable<ID> {
    //https://github.com/spring-projects/spring-data-r2dbc/issues/275
    @org.springframework.data.annotation.Transient
    private var isNew = true

    @Id
    override fun getId() = id

    override fun isNew() = isNew

}