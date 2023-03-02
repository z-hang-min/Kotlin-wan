package com.zm.module_login

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.zm.module_base.BaseApplication

/**
 * created by zm on 2023/3/2

 * @Description:

 */
class LApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}