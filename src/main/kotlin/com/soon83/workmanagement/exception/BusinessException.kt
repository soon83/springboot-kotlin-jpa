package com.soon83.workmanagement.exception

class BusinessException : RuntimeException {
    var errorCode: ErrorCode
    var errorMessage: String

    constructor(errorCode: ErrorCode) : super(errorCode.errorMessage) {
        this.errorCode = errorCode
        this.errorMessage = errorCode.errorMessage
    }

    constructor(errorCode: ErrorCode, fieldName: String?) : super(
        String.format("%s %s", errorCode.errorMessage, fieldName)
    ) {
        this.errorCode = errorCode
        this.errorMessage = String.format("%s %s", errorCode.errorMessage, fieldName)
    }

    constructor(errorCode: ErrorCode, fieldName: String?, message: String?) : super(
        String.format("%s %s", message, fieldName)
    ) {
        this.errorCode = errorCode
        this.errorMessage = String.format("%s %s", message, fieldName)
    }
}