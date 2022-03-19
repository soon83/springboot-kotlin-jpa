package com.soon83.workmanagement.exception

import com.soon83.workmanagement.dto.Res
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ExceptionHandlerAdvice {

    /**
     * JSON parse error
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException?): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(ErrorCode.INVALID_JSON_INPUT, ErrorResponse.FieldError.of())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Res.fail(response))
    }

    /**
     * binding error
     */
    @ExceptionHandler(BindException::class)
    fun handleBindException(e: BindException): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(ErrorCode.BIND_ERROR, e.bindingResult)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Res.fail(response))
    }

    /**
     * binding error in HttpMessageConverter
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.bindingResult)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Res.fail(response))
    }

    /**
     * enum type 일치하지 않을 때
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException?): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(e!!)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Res.fail(response))
    }

    /**
     * 데이터 무결성 제약조건 위반
     */
    @ExceptionHandler(DataIntegrityViolationException::class)
    protected fun handleDataIntegrityViolationException(e: DataIntegrityViolationException?): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(ErrorCode.DATA_INTEGRITY_VIOLATION_ERROR)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Res.fail(response))
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 때
     */
    @ExceptionHandler(
        HttpRequestMethodNotSupportedException::class,
        MethodNotAllowed::class
    )
    protected fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException?): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED)
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(Res.fail(response))
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않았을 때
     */
    @ExceptionHandler(AccessDeniedException::class)
    protected fun handleAccessDeniedException(e: AccessDeniedException?): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(ErrorCode.FORBIDDEN)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Res.fail(response))
    }

    /**
     * 비즈니스 로직 오류
     */
    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(e: BusinessException): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(e.errorCode, e.errorMessage)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Res.fail(response))
    }

    /**
     * 런타임 오류
     */
    @ExceptionHandler(RuntimeException::class)
    protected fun handleException(e: Exception?): ResponseEntity<Res<ErrorResponse?>> {
        val response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Res.fail(response))
    }
}