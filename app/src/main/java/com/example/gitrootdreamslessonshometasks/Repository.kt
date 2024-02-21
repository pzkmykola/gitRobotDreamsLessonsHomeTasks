package com.example.gitrootdreamslessonshometasks

import retrofit2.Response
import javax.inject.Singleton

@Singleton
class Repository (private val apiClient: ApiClient) {
    suspend fun getWeatherForecastByCityName(cityName:String) : Response<WeatherForecastResponse> {
        val apiInterface = apiClient.client.create(ApiInterface::class.java)
        return apiInterface.getWeatherForecastByCityName(cityName)
    }
}