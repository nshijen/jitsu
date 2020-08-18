package com.fantasyjitsu.api

import com.fantasyjitsu.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(50, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(80, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()

    fun <T> createRetrofitService(clazz: Class<T>?): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(clazz)
    }
}