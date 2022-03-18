package com.soon83.workmanagement.dto

import com.soon83.workmanagement.enumcode.ResultCode

data class Result<T> {

    val success: Boolean
    val code: String
    val message: String
    val data: T?

    companion object {

        fun success(): Result<Any> {
            return Result(
                success = ResultCode.SUCCESS.success,
                code = ResultCode.SUCCESS.name,
                message = ResultCode.SUCCESS.title,
                data = null,
            )
        }

        fun <T> success(data: T): Result<Any> {
            return Result(resultCode = ResultCode.SUCCESS, data = data)
        }

        fun fail(): Result<Any> {
            return Result(resultCode = ResultCode.FAIL)
        }

        fun <T> fail(data: T): Result<Any> {
            return Result(resultCode = ResultCode.FAIL, data = data)
        }
    }
}