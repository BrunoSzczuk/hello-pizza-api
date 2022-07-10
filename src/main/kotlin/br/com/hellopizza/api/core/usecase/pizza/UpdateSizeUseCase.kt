package br.com.hellopizza.api.core.usecase.pizza

import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult
import br.com.hellopizza.api.core.usecase.pizza.dto.UpdateSizeCommand

interface UpdateSizeUseCase : ResultCommandUseCase<UpdateSizeCommand, SizeResult>