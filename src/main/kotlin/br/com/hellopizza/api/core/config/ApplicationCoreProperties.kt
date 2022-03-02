package br.com.hellopizza.api.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties

@ConfigurationProperties(prefix = "application.core", ignoreUnknownFields = false)
@EnableConfigurationProperties(value = [ApplicationCoreProperties::class])
class ApplicationCoreProperties {
    val error = ErrorProperties()

    class ErrorProperties {
        val sizeAlreadyExistsKey: String? = null
    }

}