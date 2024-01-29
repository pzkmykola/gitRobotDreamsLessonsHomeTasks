package com.example.gitrootdreamslessonshometasks

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("weather/Kyiv")
    fun getWeatherForecast():Call<WeatherForecastResponse>
}