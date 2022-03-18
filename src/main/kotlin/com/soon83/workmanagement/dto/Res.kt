package com.soon83.workmanagement.dto

import com.soon83.workmanagement.enumcode.ResultCode

data class Res<T>(
    val success: Boolean? = null,
    val code: String? = null,
    val message: String? = null,
    val data: T? = null,
) {

    constructor(resultCode: ResultCode): this(
        success = resultCode.success,
        code = resultCode.name,
        message = resultCode.title,
    )

    constructor(resultCode: ResultCode, data: T): this(
        success = resultCode.success,
        code = resultCode.name,
        message = resultCode.title,
        data = data,
    )

    companion object {

        fun success(): Res<Any> {
            return Res(ResultCode.SUCCESS)
        }

        fun <T> success(data: T): Res<T> {
            return Res(ResultCode.SUCCESS, data = data)
        }

        fun fail(): Res<Any> {
            return Res(ResultCode.FAIL)
        }

        fun <T> fail(data: T): Res<T> {
            return Res(ResultCode.FAIL, data = data)
        }
    }
}