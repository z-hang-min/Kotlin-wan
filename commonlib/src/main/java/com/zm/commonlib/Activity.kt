package com.zm.kotlintest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast

//内联函数，加reified，防止范型擦除
inline fun <reified T : Activity> Context.kStartActivity() {
    var intent: Intent = Intent(this, T::class.java)
    startActivity(intent)

}


inline fun <reified T : Any> Context.kStartActivityForResult(
    requestCode: Int,
    params: () -> Array<out Pair<String, Any>>
) {
    if (this is Activity) {
        val intent = makeIntent(this, T::class.java, params)
        startActivityForResult(intent, requestCode)
    }
}

inline fun makeIntent(
    context: Context,
    targetClass: Class<*>,
    params: () -> Array<out Pair<String, Any>>
): Intent = Intent(context, targetClass).apply {
    val arry = params()
    for ((_, value) in arry.withIndex()) {
        makeParams(value)
    }
}

inline fun Intent.makeParams(it: Pair<String, Any>) {
    // from anko
    when (val value = it.second) {
        is Int -> putExtra(it.first, value)
        is Long -> putExtra(it.first, value)
        is CharSequence -> putExtra(it.first, value)
        is String -> putExtra(it.first, value)
        is Float -> putExtra(it.first, value)
        is Double -> putExtra(it.first, value)
        is Char -> putExtra(it.first, value)
        is Short -> putExtra(it.first, value)
        is Boolean -> putExtra(it.first, value)
        is java.io.Serializable -> putExtra(it.first, value)
        is Bundle -> putExtra(it.first, value)
        is Parcelable -> putExtra(it.first, value)
        is Array<*> -> when {
            value.isArrayOf<CharSequence>() -> putExtra(it.first, value)
            value.isArrayOf<String>() -> putExtra(it.first, value)
            value.isArrayOf<Parcelable>() -> putExtra(it.first, value)
            else -> throw IllegalArgumentException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        is IntArray -> putExtra(it.first, value)
        is LongArray -> putExtra(it.first, value)
        is FloatArray -> putExtra(it.first, value)
        is DoubleArray -> putExtra(it.first, value)
        is CharArray -> putExtra(it.first, value)
        is ShortArray -> putExtra(it.first, value)
        is BooleanArray -> putExtra(it.first, value)
        else -> throw IllegalArgumentException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
    }
}

inline fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}