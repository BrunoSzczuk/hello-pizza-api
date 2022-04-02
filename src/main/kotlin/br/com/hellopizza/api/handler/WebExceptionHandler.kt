package br.com.hellopizza.api.handler

import br.com.hellopizza.api.core.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class WebExceptionHandler {

    @ExceptionHandler
    (value = [WebExchangeBindException::class])
    fun handleMethodArgumentNotValidException(
            webExchangeBindException: WebExchangeBindException
    ): ResponseEntity<Any> {
        val fieldErrs = webExchangeBindException.fieldErrors
                .map { SimpleError(path = it.field, code = it.code, message = it.defaultMessage) }
                .toList()
        val errors = Errors(
                code = "validation_failure",
                message = "Validation failed.",
                errors = fieldErrs
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors)
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun handleNotFound(notFoundException: NotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse(message = notFoundException.message ?: "Not found."))
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationException(constraintViolationException: ConstraintViolationException): ResponseEntity<Any> {
        val fieldErrs = constraintViolationException.constraintViolations
                .map {
                    SimpleError(
                            path = it.propertyPath.joinToString("."),
                            code = it.constraintDescriptor.annotation.annotationClass.simpleName,
                            message = it.message
                    )
                }
                .toList()
        val errors = Errors(
                code = "validation_failure",
                message = "Validation failed.",
                errors = fieldErrs
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors)
    }
}

data class Errors(val code: String,
                  val message: String,
                  val errors: List<SimpleError>? = emptyList())

data class SimpleError(val path: String? = null,
                       val code: String? = null,
                       val message: String? = null)

data class ExceptionResponse(val message: String)