package br.com.hellopizza.api.core.gateway.pizza

import br.com.hellopizza.api.core.gateway.BasicGateway
import br.com.hellopizza.api.dataprovider.pizza.model.IdToppingEntity
import br.com.hellopizza.api.dataprovider.pizza.model.ToppingEntity

interface ToppingGateway : BasicGateway<ToppingEntity, Long>