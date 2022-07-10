package br.com.hellopizza.api.core.usecase.pizza

import br.com.hellopizza.api.core.usecase.pizza.dto.CreateSizeCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.SizeResult

interface CreateSizeUseCase : ResultCommandUseCase<CreateSizeCommand, SizeResult>