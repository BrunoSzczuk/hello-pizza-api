package br.com.hellopizza.api.core.usecase.pizza.rule

import br.com.hellopizza.api.core.domain.pizza.Size

interface CreateSizeValidationRule : SizeValidateRule<Size> {
}