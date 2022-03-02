package br.com.hellopizza.api.core.gateway.pizza

import br.com.hellopizza.api.core.gateway.BasicGateway
import br.com.hellopizza.api.dataprovider.pizza.model.PizzaEntity

interface PizzaGateway : BasicGateway<PizzaEntity, Long>