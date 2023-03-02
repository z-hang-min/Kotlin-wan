package com.zm.module_base.network

/**
 * created by zm on 2023/3/2

 * @Description:

 */
interface IBaseResult<T> {
    val errorCode: Int
    val data: T
    val errorMsg: String
    val isSuccess: Boolean
}

