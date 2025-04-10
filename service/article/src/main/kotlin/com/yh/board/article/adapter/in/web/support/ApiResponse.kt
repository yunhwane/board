package com.yh.board.article.adapter.`in`.web.support

import org.springframework.http.HttpStatus

data class ApiResponse<T> (
    val code: Int,
    val message: String,
    val data: T? = null,
){

    companion object {
        fun <T> with(httpStatus: HttpStatus, message: String, data: T): ApiResponse<T> {
            return ApiResponse(
                code = httpStatus.value(),
                message = message,
                data = data
            )
        }
    }
}