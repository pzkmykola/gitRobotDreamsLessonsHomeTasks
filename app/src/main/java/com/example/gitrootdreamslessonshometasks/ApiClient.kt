package com.example.gitrootdreamslessonshometasks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        val client = Retrofit.Builder()
            .baseUrl("https://goweather.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}