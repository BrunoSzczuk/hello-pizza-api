package br.com.hellopizza.api.dataprovider.pizza.converter

import br.com.hellopizza.api.core.domain.pizza.Topping
import br.com.hellopizza.api.dataprovider.base.BaseEntityConverter
import br.com.hellopizza.api.dataprovider.pizza.model.ToppingEntity
import org.mapstruct.Mapper

@Mapper
interface ToppingEntityConverter : BaseEntityConverter<Topping, ToppingEntity>