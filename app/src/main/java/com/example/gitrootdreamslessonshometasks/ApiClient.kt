package com.example.gitrootdreamslessonshometasks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class ApiClient  @Inject constructor() {
class ApiClient {
    companion object {
        val client = Retrofit.Builder()
            .baseUrl("https://goweather.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}