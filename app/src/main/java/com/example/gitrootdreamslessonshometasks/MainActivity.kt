package com.example.gitrootdreamslessonshometasks

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.gitrootdreamslessonshometasks.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: MyViewModel
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myPhone.text = "  My phone number is +38-067-681-76-51"

        binding.sendEmailButton.setOnClickListener {
            val toast = Toast.makeText(this, "My email: mykola.pazuk@gmail.com", Toast.LENGTH_LONG)
            toast.show()
        }

        binding.imageView.setOnClickListener {
            val toast = Toast.makeText(this, "Lviv is the best city of Ukraine!!!", Toast.LENGTH_LONG)
            toast.show()
        }

        binding.buttonWeatherForecast.setOnClickListener {
            val nameOfCity: String = binding.cityName.text.toString()
            viewModel.getData(nameOfCity)
        }

        viewModel.uiState.observe(this){
            when(it){
                is MyViewModel.UIState.Empty -> {

                    Toast.makeText(this, "Empty response", Toast.LENGTH_SHORT).show()
                }
                is MyViewModel.UIState.Processing -> {
                    binding.progressBar.visibility = VISIBLE
                    //Toast.makeText(this, "Processing...", Toast.LENGTH_SHORT).show()
                }
                is MyViewModel.UIState.Result -> {
                    val weather = mapToDisplayItem(it.weather)
                    binding.progressBar.visibility = INVISIBLE
                    binding.weatherTemperatureTextView.text = weather.temperature
                    binding.weatherWindTextView.text = weather.wind
                    binding.weatherDescriptionTextView.text = weather.wind
                }
                is MyViewModel.UIState.Error -> {
                    binding.progressBar.visibility = INVISIBLE
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

data class WeatherForecastResponse(val temperature: String, val wind: String, val description: String)

data class DisplayWeatherToday(val temperature:String, val wind:String, val description:String)

fun mapToDisplayItem(response: WeatherForecastResponse):DisplayWeatherToday{
    val temperature = "Temperature is ${response.temperature}"
    val wind = "Wind is ${response.wind}"
    val description = "In general, ${response.description} "
    return DisplayWeatherToday(temperature, wind, description)
}