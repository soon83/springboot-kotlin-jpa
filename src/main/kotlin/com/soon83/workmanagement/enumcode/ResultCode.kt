package com.soon83.workmanagement.enumcode

enum class ResultCode(
    val success: Boolean,
    val title: String,
) {
    SUCCESS(true, "요청이 성공하였습니다"),
    FAIL(false, "요청이 실패하였습니다"),
}