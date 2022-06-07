package com.example.registration.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registration.data.SharedPref
import com.example.registration.model.request.SignInReq
import com.example.registration.model.response.SignInRes
import com.example.registration.network.api.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class LoginFragmentViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var sharedPref: SharedPref

    fun signIn(signInReq: SignInReq): MutableLiveData<Any> {
        val resultLiveData: MutableLiveData<Any> = MutableLiveData()
        val call: Call<SignInRes> = RetrofitClient.getInstance().getApi().SignInRes_(
            signInReq
        )
        call.enqueue(object : Callback<SignInRes> {
            override fun onResponse(call: Call<SignInRes>, response: Response<SignInRes>) {
                if (response.isSuccessful) {
                    resultLiveData.value = response.body()
                } else {
                   // resultLiveData.value = parseError(response)
                }
            }

            override fun onFailure(call: Call<SignInRes>, t: Throwable) {
                resultLiveData.value = t
            }
        })
        return resultLiveData
    }

}