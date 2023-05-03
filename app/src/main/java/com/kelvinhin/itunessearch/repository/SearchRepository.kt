package com.kelvinhin.itunessearch.repository

import com.kelvinhin.itunessearch.BuildConfig
import com.kelvinhin.itunessearch.api.ITunesApi
import com.kelvinhin.itunessearch.constants.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchRepository {
    private fun createHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val headerInterceptor = HttpLoggingInterceptor()
            headerInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            okHttpClientBuilder.addInterceptor(headerInterceptor)
            //Enable Body Interceptor to show API call body in log if needed
            val bodyInterceptor = HttpLoggingInterceptor()
            bodyInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(bodyInterceptor)
        }

        return okHttpClientBuilder.build()
    }

    val iTunesApi: ITunesApi = Retrofit.Builder()
        .baseUrl(Url.BASE)
        .client(createHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ITunesApi::class.java)
}