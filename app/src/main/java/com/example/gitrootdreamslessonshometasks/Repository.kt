package com.example.gitrootdreamslessonshometasks

import retrofit2.Response
import retrofit2.Retrofit

class Repository (private val client: Retrofit) {
    suspend fun getWeatherForecastByCityName(cityName:String) : Response<WeatherForecastResponse> {
        val apiInterface = client.create(ApiInterface::class.java)
        return apiInterface.getWeatherForecastByCityName(cityName)
    }
}