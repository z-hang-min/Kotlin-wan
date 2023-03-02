package com.zm.kotlintest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zm.kotlintest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/app/MainActivity")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        fun openActivity(activity: Activity) {
            activity.kStartActivity<MainActivity>()
        }
    }

    private lateinit var binder: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        initView()
    }

    private fun initView() {
        binder.run {
            btnJump.setOnClickListener {
                ARouter.getInstance().build("/login/LoginActivity").navigation()
            }
        }
//        val list1= arrayListOf<Int>(1,2,3,4)
        val list1 = mutableListOf<Int>(1, 2, 3, 4)
//        val list2= arrayListOf<String>("one","two","threee")
        val list2 = mutableListOf<String>("one", "two", "threee")
        printList(list1)
        Log.e("zhangmin", printList2(list2))

    }

    //out 协变，只能作用输出数据，不能修改;in 修饰的参数只能修改不能输出
    private fun <T> printList(list: MutableList<in T>): Unit {
        for (ele in list) {
            Log.e("zhangmin", "ele==$ele")
        }
//        return list[0]

    }

    private fun <T> printList2(list: MutableList<out T>): T {

        return list[0]

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            val name = data.getStringExtra("backName")
            binder.tvDesc.text = name
        } else {
            binder.tvDesc.text = "data==null"
        }

        super.onActivityResult(requestCode, resultCode, data)

    }
}