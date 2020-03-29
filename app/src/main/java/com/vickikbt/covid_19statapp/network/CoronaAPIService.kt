package com.vickikbt.covid_19statapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://corona.lmao.ninja/"

interface CoronaAPIService {

    @GET("all")
    fun getGlobalStatistics(): Deferred<GlobalCoronaData>

    companion object {
        operator fun invoke(): CoronaAPIService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CoronaAPIService::class.java)
        }
    }
}
