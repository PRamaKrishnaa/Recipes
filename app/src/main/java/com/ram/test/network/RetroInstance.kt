package com.ram.test.network

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient as OkHttpClient1

class RetroInstance {

    companion object {
        val baseURL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"

        //To print request and response in Logcat
        val okHttpClient = OkHttpClient1().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
}