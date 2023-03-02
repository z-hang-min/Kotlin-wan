package com.zm.module_login.http

import com.zm.module_base.network.BaseResult
import com.zm.module_login.http.bean.UserInfo
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * created by zm on 2023/3/2

 * @Description:

 */
interface LoginService {
    @POST("user/login")
    @JvmSuppressWildcards
    @FormUrlEncoded
    suspend fun login(@FieldMap map: Map<String, Any>): BaseResult<UserInfo>
}