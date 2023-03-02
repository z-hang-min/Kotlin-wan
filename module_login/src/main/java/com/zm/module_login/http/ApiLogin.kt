package com.zm.module_login.http

import com.zm.module_base.network.BaseResult
import com.zm.module_base.network.KNetWork
import com.zm.module_base.network.KResults
import com.zm.module_base.network.ServiceCreator
import com.zm.module_login.http.bean.UserInfo

/**
 * created by zm on 2023/3/1

 * @Description:

 */
object ApiLogin : KNetWork() {
    private var service: LoginService = ServiceCreator.create()
    suspend fun login(account: String, pwd: String): KResults<UserInfo> {
        return processApi {
            val map = mutableMapOf<String, Any>(
                "username" to account,
                "password" to pwd,

            )
            val result = service.login(map)
            result
        }
    }

}