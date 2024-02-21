package com.example.gitrootdreamslessonshometasks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/weather/{cityName}")
    suspend fun getWeatherForecastByCityName(@Path("cityName") cityName: String) : Response<WeatherForecastResponse>
}