package com.zm.kotlintest
import com.alibaba.android.arouter.launcher.ARouter
import com.zm.module_base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * created by zm on 2023/3/2

 * @Description:

 */
@HiltAndroidApp
class MyApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()    // Print log
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

}