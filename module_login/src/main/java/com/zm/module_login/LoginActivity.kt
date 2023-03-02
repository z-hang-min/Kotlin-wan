package com.zm.module_login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zm.kotlintest.kStartActivity
import com.zm.kotlintest.showToast
import com.zm.module_login.databinding.ActivityLoginBinding
import com.zm.module_login.viewmodel.LoginViewModel

@Route(path = "/login/LoginActivity")
class LoginActivity : AppCompatActivity() {

    companion object {
        fun openActivity(activity: Activity) {
            activity.kStartActivity<LoginActivity>()
        }
    }

    private lateinit var viewBinding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        with(viewBinding) {
            btnLogin.setOnClickListener {
                viewModel.doLogin()
            }
            etAccount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    viewModel.account.value = s.toString()
                }

            })
            etPwd.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    viewModel.pwd.value = s.toString()
                }

            })
        }

        viewModel.userInfo.observe(this) {
            if (it == null) {
                showToast("登陆失败")
            } else {
                ARouter.getInstance().build("/app/MainActivity").navigation()
                finish()
            }

        }
        viewModel.errorMsg.observe(this) {
            viewModel.errorMsg.value?.let { it1 -> showToast(it1) }
        }
    }
}