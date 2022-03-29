package br.com.hellopizza.api.core.usecase.pizza.dto.converter

import br.com.hellopizza.api.core.domain.pizza.Size
import br.com.hellopizza.api.core.usecase.BaseDTOConverter
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeSumarryDTO
import org.mapstruct.Mapper

@Mapper
interface SizeDTOConverter : BaseDTOConverter<Size, SizeSumarryDTO> {
}