package com.example.gitrootdreamslessonshometasks

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        val client = Retrofit
            .Builder()
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://goweather.herokuapp.com")
            .build()
    }
}