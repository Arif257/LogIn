package com.example.registration.ui.login

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.registration.R
import com.example.registration.ui.fragment.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        replaceFramgentWithoutBackStack(this@LoginActivity, LoginFragment.newInstance())
    }


    fun replaceFramgentWithoutBackStack(activity: Activity, fragment: Fragment?) {
        val fragmentManager1 = (activity as FragmentActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager1.beginTransaction()
        fragmentTransaction.replace(R.id.container_activity_main, fragment!!)
        fragmentTransaction.commitAllowingStateLoss()
    }
}

