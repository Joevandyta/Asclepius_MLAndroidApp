package com.dicoding.asclepius.api

import com.dicoding.asclepius.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.Base_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance = retrofit.create(ApiService::class.java)
}