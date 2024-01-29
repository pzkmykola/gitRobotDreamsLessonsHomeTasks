package com.example.gitrootdreamslessonshometasks

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("weather/Odessa")
    fun getWeatherForecast():Call<WeatherForecastResponse>

    @GET("weather/Odessa")
    fun getWeatherForecastRx():Single<WeatherForecastResponse>
}