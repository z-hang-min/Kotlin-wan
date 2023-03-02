package com.zm.kotlintest.http.data


data class CheckUpdateResult(
    val appId: Int,
    val clientType: String,
    val createTime: String,
    val desc: List<String>,
    val downloadUrl: String,
    val id: Int,
    val remarks: String,
    val update: Boolean,
    val updateTime: String,
    val updateType: Int,
    val version: String
)