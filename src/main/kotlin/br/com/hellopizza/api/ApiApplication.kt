package br.com.hellopizza.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

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
