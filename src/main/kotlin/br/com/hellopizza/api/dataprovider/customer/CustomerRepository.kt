package br.com.hellopizza.api.dataprovider.customer

import br.com.hellopizza.api.core.domain.customer.Customer
import br.com.hellopizza.api.core.domain.customer.IdCustomer
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CustomerRepository : ReactiveCrudRepository<Customer, IdCustomer> {
}