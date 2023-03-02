package com.zm.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.zm.kotlintest.databinding.ActivitySplashBinding
import java.lang.ref.WeakReference

class SplashActivity : AppCompatActivity() {
    private val mHandler = MyHandler(WeakReference(this@SplashActivity))
    private lateinit var binding: ActivitySplashBinding
    var totalTime: Long = 3000
    var countDownTimer = object : CountDownTimer(totalTime, 1) {
        //1000ms运行一次onTick里面的方法
        override fun onFinish() {
            Log.d(TAG, "==倒计时结束")
//            MainActivity.openActivity(this@SplashActivity)
            ARouter.getInstance().build("/login/LoginActivity").navigation()
            finish()
        }

        override fun onTick(millisUntilFinished: Long) {
            binding.btnJump.text = "跳过 （${millisUntilFinished/1000}）s"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        runnable = Runnable {
//            MainActivity.openActivity(this)
//            finish()
//        }
    }

    companion object {
        private const val TAG = "SplashActivity"
    }

//    private lateinit var runnable: Runnable

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
//        mHandler.removeCallbacks(runnable)//handler删除线程
        countDownTimer.cancel()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
//        mHandler.postDelayed(runnable, 3000)
        countDownTimer.start()
    }

    private class MyHandler(val wrActivity: WeakReference<SplashActivity>) :
        Handler(Looper.getMainLooper()) {

    }


}
