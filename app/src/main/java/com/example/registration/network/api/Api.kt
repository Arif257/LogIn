package com.example.registration.network.api

import com.example.registration.model.request.SignInReq
import com.example.registration.model.response.SignInRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("auth/local/signin")
    fun SignInRes_(
        @Body singInReq: SignInReq
    ): Call<SignInRes>
}