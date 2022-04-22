package me.osmanfurkan.licenseapi.exception

import java.time.LocalDateTime
import java.util.function.Consumer
import javax.persistence.EntityNotFoundException
import javax.persistence.PersistenceException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GeneralExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest,
    ): ResponseEntity<Any> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(
            Consumer { error: ObjectError ->
                val fieldName = (error as FieldError).field
                errors[fieldName] = error.getDefaultMessage()
            }
        )
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun entityNotFoundExceptionHandler(exception: PersistenceException): ResponseEntity<*> {
        logger.error(
            "${exception.javaClass.simpleName} was thrown at ${LocalDateTime.now()}, message: ${exception.message}"
        )
        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }
}
