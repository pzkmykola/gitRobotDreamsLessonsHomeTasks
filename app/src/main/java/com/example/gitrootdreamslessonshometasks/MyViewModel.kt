package com.example.gitrootdreamslessonshometasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _uiState = MutableLiveData<UIState>(UIState.Empty)
    val uiState: LiveData<UIState> = _uiState

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