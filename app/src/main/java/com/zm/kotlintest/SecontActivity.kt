package com.zm.kotlintest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import com.zm.kotlintest.databinding.ActivitySecontBinding

class SecontActivity : AppCompatActivity() {

    companion object {
        fun openActivity(activity: Activity, isFirst: Boolean, name: String) {
            activity.kStartActivityForResult<SecontActivity>(request_code) {
                arrayOf(key_isFirst to isFirst, key_name to name)
            }

        }

        const val request_code = 300
        const val key_isFirst: String = "isFrirst"
        const val key_name: String = "name"

    }

    private lateinit var binding: ActivitySecontBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecontBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val isFirst = intent.getBooleanExtra(key_isFirst, false)
        val name = intent.getStringExtra(key_name)
        Log.e("zhangmin", "isFirst==$isFirst")
        binding.tvDesc.text = "$name is $isFirst"

    }


    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("backName", "i am back")
        setResult(Activity.RESULT_OK, intent)
        Log.e("zhangmin", "back")
        finish()
    }
}