package br.com.hellopizza.api.core.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.core", ignoreUnknownFields = false)
class ApplicationCoreProperties {
    val error = ErrorProperties()

    class ErrorProperties {
        var sizeAlreadyExistsKey: String? = null
        var toppingAlreadyExistsKey: String? = null
    }

}