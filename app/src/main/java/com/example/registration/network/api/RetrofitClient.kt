package com.example.registration.network.api

import com.example.registration.data.SharedPref
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitClient @Inject constructor() {

    var sharedPref = SharedPref()

    companion object {
        private var mInstance: RetrofitClient? = null

        private var retrofit: Retrofit? = null
        private var retrofit2: Retrofit? = null

        @Synchronized
        fun getInstance(): RetrofitClient {
            if (mInstance == null)
                mInstance = RetrofitClient()
            return mInstance as RetrofitClient
        }
    }

    init{
        getNewRetrofitClient()
    }
//    init {
//
//        val authenticator = TokenAuthenticator(sharedPref, buildTokenApi())
//
//
//
//        if (retrofit == null)
//            retrofit = Retrofit.Builder()
//                .baseUrl(ServerInfo.BASE_URL)
//                .client(getRetrofitClient(authenticator))
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//
//        if (retrofit2 == null)
//            retrofit2 = Retrofit.Builder()
//                .baseUrl(ServerInfo.BASE)
//                .client(getRetrofitClient(authenticator))
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//
//
//    }

    fun getApi(): Api {
        return getNewRetrofitClient()!!.create(Api::class.java)
    }

    fun getRetrofit(): Retrofit {
        return getNewRetrofitClient()!!
    }


    fun getApi2(): Api {
        return getNewRetrofitClient()!!.create(Api::class.java)
    }

    fun getRetrofit2(): Retrofit {
        return retrofit2!!
    }

    private fun buildTokenApi(): Api {

        val okHttpClient = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ServerInfo.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Content-Type", "application/json")
                    it.addHeader("Authorization", sharedPref.loginToken!!)
                    //it.addHeader("Authorization","")
                }.build())
            }

            .authenticator(authenticator!!)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

            .build()

        return okHttpClient
    }

    fun getNewRetrofitClient(): Retrofit? {
        val authenticator = TokenAuthenticator(SharedPref(), buildTokenApi())

        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(ServerInfo.BASE_URL)
                .client(getRetrofitClient(authenticator))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        if (retrofit2 == null)
            retrofit2 = Retrofit.Builder()
                .baseUrl(ServerInfo.BASE)
                .client(getRetrofitClient(authenticator))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit
    }

}