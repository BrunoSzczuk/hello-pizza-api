package br.com.hellopizza.api.dataprovider.pizza.converter

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.dataprovider.base.BaseEntityConverter
import br.com.hellopizza.api.dataprovider.pizza.model.SizeEntity
import org.mapstruct.Mapper

@Mapper
interface SizeEntityConverter : BaseEntityConverter<Size, SizeEntity>