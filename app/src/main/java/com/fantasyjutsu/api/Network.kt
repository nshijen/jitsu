package com.fantasyjutsu.api

import com.fantasyjutsu.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
@Module
@InstallIn(ActivityComponent::class)
object Network {
    @Provides
    fun provideOkHttpClient():OkHttpClient{
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(80, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()
        return okHttpClient
    }


    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesApi(retrofit: Retrofit):  Api{
        return retrofit.create(Api::class.java)
    }
}