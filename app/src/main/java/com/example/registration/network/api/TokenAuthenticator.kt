package com.example.registration.network.api

import android.util.Log
import com.example.registration.data.SharedPref
import com.example.registration.model.request.SignInReq
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Route

class TokenAuthenticator(
    private val pref: SharedPref,
    tokenApi: Api?
) : Authenticator {

    private val api = tokenApi
    private val userPreferences = pref

    override fun authenticate(route: Route?, response: okhttp3.Response): Request {

        val res = getUpdatedToken()
        var token = ""
        token = res;
        userPreferences.loginToken = token
        Log.d("TAG", " onResponse: $token")

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${token}")
            .build()


    }

    private fun getUpdatedToken(): String {
        //    val refreshToken = userPreferences
        Log.d("TAG", "getUpdatedToken:  calling token ${pref.email} ${pref.pass}")

        if (api != null) {
            val authTokenResponse = api.SignInRes_(
                SignInReq(
                    pref.email!!,
                    pref.pass!!
                )
            ).execute().body()

            return authTokenResponse?.payload?.access_token.toString()
        }

        return "";
    }

}