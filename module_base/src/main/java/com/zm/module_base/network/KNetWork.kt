package com.zm.module_base.network

import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import javax.net.ssl.SSLException

/**
 * created by zm on 2023/3/1

 * @Description:

 */
open class KNetWork {
    suspend fun <T> processApi(block: suspend () -> BaseResult<T>): KResults<T> {
        return with(Dispatchers.IO) {
            val result = block()
            try {
                if (result.isSuccess) {
                    KResults.success(result.data)
                } else {
                    KResults.failure(Exception(result.errorMsg), result.errorCode, result.errorMsg)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                val exceptionHandle = KErrors.handleException(e)
                KResults.failure<T>(exceptionHandle, exceptionHandle.code, exceptionHandle.desc)
            }

        }
    }
}

sealed class KErrors : Throwable() {
    data class NetworkError(val error: Throwable, var code: Int = -1, var desc: String = "") :
        KErrors()

    object EmptyInputError : KErrors()
    object EmptyResultsError : KErrors()
    object SingleError : KErrors()


    companion object {
        fun handleException(e: Throwable): NetworkError {
            val error = NetworkError(e)

            if (e is HttpException) {
                val httpException: HttpException = e
                when (httpException.code()) {
                    401 -> error.desc = "操作未授权"
                    403 -> error.desc = "请求被拒绝"
                    404 -> error.desc = "资源不存在"
                    408 -> error.desc = "服务器执行超时"
                    500 -> error.desc = "服务器内部错误"
                    503 -> error.desc = "服务器不可用"
                    else -> error.desc = "网络错误"
                }
                error.code = httpException.code()
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException || e is MalformedJsonException
            ) {
                error.desc = "解析错误"
                error.code = -1001
            } else if (e is ConnectException) {
                error.desc = "连接失败"
                error.code = -1002
            } else if (e is SSLException) {
                error.code = -1003
                error.desc = "证书验证失败"
            } else if (e is IOException) {
                error.desc = "连接超时"
                error.code = -1004
            } else if (e is SocketTimeoutException) {
                error.desc = "连接超时"
                error.code = -1004
            } else if (e is UnknownHostException) {
                error.desc = "主机地址未知"
                error.code = -1004
            } else {
                error.desc = "未知错误"
                error.code = -1000
            }

            return error

        }
    }


}