package com.zm.module_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.zm.commonlib.ToastUtils.showToast
import com.zm.module_base.network.KResults
import com.zm.module_login.http.ApiLogin
import com.zm.module_login.http.bean.UserInfo
import kotlinx.coroutines.launch

/**
 * created by zm on 2023/3/2

 * @Description:

 */
class LoginViewModel : ViewModel() {
    val userInfo: MutableLiveData<UserInfo?> = MutableLiveData()
    val account: MutableLiveData<String> = MutableLiveData()
    val pwd: MutableLiveData<String> = MutableLiveData()
    val errorMsg: MutableLiveData<String> = MutableLiveData()

    fun doLogin() {
        if (account.value == null || pwd.value == null) {
            return
        } else {
            viewModelScope.launch {
                when (val result = ApiLogin.login(account.value!!, pwd.value!!)) {
                    is KResults.Success -> {
                        userInfo.value = result.data
                    }
                    is KResults.Failure -> {
                        errorMsg.value=result.msg
                    }
                }
            }
        }

    }
}