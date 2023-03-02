package com.zm.module_base.network

import com.google.gson.GsonBuilder
import com.shshcom.module_base.network.ArraySecurityAdapter
import com.zm.module_base.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * created by zm on 2023/3/2

 * @Description:

 */
object ServiceCreator {
//    private const val BASE_URL = "http://104.217.5.245:8000/"
    private const val BASE_URL = "https://www.wanandroid.com/"

    private fun getOkHttpClient(): OkHttpClient {
        val  builder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor( HttpLogInterceptor());
        }

        return builder.build()
    }

    val gson = GsonBuilder()
        .registerTypeHierarchyAdapter(List::class.java, ArraySecurityAdapter())
        .create()

    private val retrofit = Retrofit.Builder()
        .client(getOkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}