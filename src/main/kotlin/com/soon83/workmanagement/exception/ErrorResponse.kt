package com.soon83.workmanagement.exception

import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

class ErrorResponse(
    val status: Int? = null,
    val error: HttpStatus? = null,
    val code: String? = null,
    val message: String? = null,
    val errors: List<FieldError>? = null,
) {

    constructor(error: ErrorCode, errors: List<FieldError>): this(
        status = error.status.value(),
        error = error.status,
        code = error.errorCode,
        message = error.errorMessage,
        errors = errors,
    )

    constructor(error: ErrorCode, errorMessage: String): this(
        status = error.status.value(),
        error = error.status,
        code = error.errorCode,
        message = errorMessage,
        errors = ArrayList()
    )

    companion object {
        fun of(code: ErrorCode): ErrorResponse {
            return ErrorResponse(code, ArrayList())
        }

        fun of(code: ErrorCode, errors: List<FieldError>): ErrorResponse {
            return ErrorResponse(code, errors)
        }

        fun of(code: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(code, FieldError.of(bindingResult))
        }

        fun of(code: ErrorCode, errorMessage: String): ErrorResponse {
            return ErrorResponse(code, errorMessage)
        }

        fun of(e: MethodArgumentTypeMismatchException): ErrorResponse {
            val value = e.value ?: ""
            val errors = FieldError.of(e.name, value.toString(), e.errorCode)
            return ErrorResponse(ErrorCode.INVALID_TYPE_VALUE, errors)
        }
    }

    class FieldError(
        val field: String? = null,
        val value: String? = null,
        val reason: String? = null,
    ) {

        companion object {
            fun of(): List<FieldError> {
                return ArrayList()
            }

            fun of(field: String, value: String, reason: String): List<FieldError> {
                val fieldErrors: MutableList<FieldError> = ArrayList()
                fieldErrors.add(FieldError(field, value, reason))
                return fieldErrors
            }

            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrors: MutableList<org.springframework.validation.FieldError> = bindingResult.fieldErrors
                /*fieldErrors.forEach { e ->
                    println("#############################################")
                    println("# error.field: ${e.field}")
                    println("# error.rejectedValue: ${e.rejectedValue}")
                    println("# error.defaultMessage: ${e.defaultMessage}")
                    println("#############################################")
                }*/
                return fieldErrors
                    .map {
                        FieldError(
                            field = it.field,
                            value = it.rejectedValue.toString(),
                            reason = it.defaultMessage.toString(),
                        )
                    }
            }
        }
    }
}