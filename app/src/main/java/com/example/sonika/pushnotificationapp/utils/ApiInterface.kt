package com.example.sonika.pushnotificationapp.utils

import com.example.sonika.pushnotificationapp.model.ExchangeRate
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("list/exchangerate")

    fun getAllExchangeRate(): Call<List<ExchangeRate>>

    companion object {

        var httpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))


        var builder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                )

        var retrofit = builder
                .client(
                        httpClient.build()
                )
                .build()
    }


}