package br.com.hellopizza.api.dataprovider.pizza.converter

import br.com.hellopizza.api.core.domain.pizza.Pizza
import br.com.hellopizza.api.dataprovider.base.BaseEntityConverter
import br.com.hellopizza.api.dataprovider.pizza.model.PizzaEntity
import org.mapstruct.Mapper

@Mapper
interface PizzaEntityConverter : BaseEntityConverter<Pizza, PizzaEntity>