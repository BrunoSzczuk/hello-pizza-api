package br.com.hellopizza.api.core.usecase.pizza

import br.com.hellopizza.api.core.usecase.pizza.dto.CreateToppingCommand
import br.com.hellopizza.api.core.usecase.pizza.dto.ToppingResult

interface CreateToppingUseCase : ResultCommandUseCase<CreateToppingCommand, ToppingResult>