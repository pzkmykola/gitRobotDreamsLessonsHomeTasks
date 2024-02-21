package com.example.gitrootdreamslessonshometasks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiClient  @Inject constructor() {
        val client: Retrofit? = Retrofit.Builder()
            .baseUrl("https://goweather.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}