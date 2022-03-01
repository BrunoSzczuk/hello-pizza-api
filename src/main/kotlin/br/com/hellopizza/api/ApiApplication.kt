package br.com.hellopizza.api

import br.com.hellopizza.api.core.config.ApplicationCoreProperties
import br.com.hellopizza.api.dataprovider.pizza.SizeRepository
import br.com.hellopizza.api.dataprovider.pizza.model.IdSizeEntity
import br.com.hellopizza.api.dataprovider.pizza.model.SizeEntity
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.math.BigDecimal

@SpringBootApplication
class ApiApplication /*{
    @Bean
    fun insertDb(sizeRepository: SizeRepository) = CommandLineRunner {
        sizeRepository.saveAll(
            listOf(
                SizeEntity(1L, "small", 2L, BigDecimal.valueOf(30L)),
                SizeEntity(2L, "medium", 3L, BigDecimal.valueOf(40L)),
                SizeEntity(3L, "big", 4L, BigDecimal.valueOf(60L))
            )
        ).blockFirst()
    }
}*/

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
