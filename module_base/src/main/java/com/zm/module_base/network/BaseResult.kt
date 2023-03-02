package com.zm.module_base.network

/**
 * created by zm on 2023/3/2

 * @Description:

 */
class BaseResult<T>(
    override val errorCode: Int,
    override val data: T,
    override val errorMsg: String,
) : IBaseResult<T> {
    override val isSuccess: Boolean
        get() = errorCode == 0
}