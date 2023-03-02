package com.zm.commonlib

import android.content.Context
import android.widget.Toast

/**
 * created by zm on 2023/3/2

 * @Description:

 */
object ToastUtils {
    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}