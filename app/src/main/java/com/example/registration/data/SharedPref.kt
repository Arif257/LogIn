package com.example.registration.data

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import com.example.registration.Btb
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPref @Inject constructor() {
    var mySharedPref: SharedPreferences
    val appContext = Btb.appContext
    var isLogin: Boolean?
        get() = mySharedPref.getBoolean("isLogin", false)
        set(isLogin) {
            val editor = mySharedPref.edit()
            editor.putBoolean("isLogin", isLogin!!)
            editor.apply()
        }
    var loginToken: String?
        get() = mySharedPref.getString("loginToken", "Bearer ")
        set(loginToken) {
            val editor = mySharedPref.edit()
            editor.putString("loginToken", "Bearer " + loginToken!!)
            editor.apply()
        }

    var email: String?
        get() = mySharedPref.getString("email", "")
        set(email) {
            val editor = mySharedPref.edit()
            editor.putString("email", email)
            editor.apply()
        }

    var pass: String?
        get() = mySharedPref.getString("pass", "")
        set(pass) {
            val editor = mySharedPref.edit()
            editor.putString("pass", pass)
            editor.apply()
        }


    var lang: String?
        get() = mySharedPref.getString("lang", "en")
        set(lang) {
            val editor = mySharedPref.edit()
            editor.putString("lang", lang)
            editor.apply()
        }

    init {
        mySharedPref = appContext.getSharedPreferences("filename", Context.MODE_PRIVATE)
    }

    fun deleteAllSharedPrefs() {
        mySharedPref.edit().clear().apply()
    }



    fun languageSet(context: Context) {
        mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE)
        setLocal(context, lang!!)
    }

    fun setDeny(deny: Boolean) {
        val editor = mySharedPref.edit()
        editor.putBoolean("deny", deny)
        editor.apply()
    }

    fun getDeny(): Boolean {
        return mySharedPref.getBoolean("deny", false)
    }


    fun setLocal(context: Context, lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
}
