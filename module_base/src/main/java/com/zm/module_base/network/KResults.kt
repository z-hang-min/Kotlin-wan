package com.zm.module_base.network

/**
 * created by zm on 2023/3/1

 * @Description:

 */
sealed class KResults<out T> {
    data class Failure(val error: Throwable, val code: Int = 1, val msg: String = "") :
        KResults<Nothing>()

    data class Success<out T>(val data: T, val msg: String = "") : KResults<T>()
    companion object {
        fun <T> success(result: T): KResults<T> = Success(result)
        fun <T> failure(error: Throwable, code: Int = 1, msg: String = ""): KResults<T> =
            Failure(error, code, msg)
    }
}
