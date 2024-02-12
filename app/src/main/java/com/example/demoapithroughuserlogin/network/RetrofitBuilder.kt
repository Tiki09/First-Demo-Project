package com.example.demoapithroughuserlogin.network

import ApiEndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiUrl.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiEndPoint by lazy {
        retrofit.create(ApiEndPoint::class.java)
    }
}