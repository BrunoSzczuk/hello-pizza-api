package br.com.hellopizza.api.core.usecase.pizza.rule

import br.com.hellopizza.api.core.domain.pizza.Topping

interface CreateToppingValidationRule : ValidateRule<Topping> {
}