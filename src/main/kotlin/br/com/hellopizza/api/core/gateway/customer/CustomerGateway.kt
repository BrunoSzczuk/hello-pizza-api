package br.com.hellopizza.api.core.gateway.customer

import br.com.hellopizza.api.core.domain.customer.Customer
import br.com.hellopizza.api.core.domain.customer.IdCustomer
import br.com.hellopizza.api.core.gateway.BasicGateway

interface CustomerGateway : BasicGateway<Customer, IdCustomer> {
}