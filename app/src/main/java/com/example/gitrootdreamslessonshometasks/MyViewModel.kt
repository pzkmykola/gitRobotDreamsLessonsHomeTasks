package com.example.gitrootdreamslessonshometasks

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MyViewModel : ViewModel() {
    private val _uiState = MutableLiveData<UIState>(UIState.Empty)
    val uiState: LiveData<UIState> = _uiState
    private val repo = MyApplication.getApp().repo
    fun getData(cityName:String) {
        _uiState.value = UIState.Processing
        viewModelScope.launch (Dispatchers.IO){
            val response = async { repo.getWeatherForecastByCityName(cityName) }.await()
            if (response.isSuccessful && response.body() != null) {
                val weather:WeatherForecastResponse = response.body()!!
                _uiState.postValue(UIState.Result(weather))
            }
            else{
                val errorMessage = response.message().toString()
                _uiState.postValue(UIState.Error(errorMessage))
            }

        }
    }

    sealed class UIState {
        object Empty : UIState()
        object Processing : UIState()
        class Result(val weather:WeatherForecastResponse) : UIState()
        class Error(val message: String) : UIState()
    }
}