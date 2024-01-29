package com.example.gitrootdreamslessonshometasks

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.myPhone)
        textView.setText("  My phone number is +38-067-681-76-51")

        val buttonEmail: Button = findViewById(R.id.sendEmailButton)
        buttonEmail.setOnClickListener {
            val toast = Toast.makeText(this, "My email: mykola.pazuk@gmail.com", Toast.LENGTH_LONG)
            toast.show()
        }

        val modifyBackground:ImageView = findViewById(R.id.imageView)
        modifyBackground.setOnClickListener {
            val toast = Toast.makeText(this, "Lviv is the best city of Ukraine!!!", Toast.LENGTH_LONG)
            toast.show()
        }

        val buttonWeatherForecast: Button = findViewById(R.id.buttonWeatherForecast)
        val textWeatherTemperature:TextView = findViewById(R.id.weatherTemperatureTextView)
        val textWeatherWind:TextView = findViewById(R.id.weatherWindTextView)
        val textWeatherDescription:TextView = findViewById(R.id.weatherDescriptionTextView)
        val editTextTo: EditText = findViewById<EditText>(R.id.enteredName)
        buttonWeatherForecast.setOnClickListener {
            val name_of_city: String = editTextTo.text.toString()
            val apiClient = ApiClient.client.create(ApiInterface::class.java)
            apiClient
                .getWeatherForecastByCityNameRx(name_of_city)
                .subscribeOn(Schedulers.io())
                .map{response -> mapToDisplayItem(response) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textWeatherTemperature.text = "${it.temperature}"
                    textWeatherWind.text = "${it.wind}"
                    textWeatherDescription.text = "${it.description}"
                },{
                    Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                })

//Commented code below represents solution with Retrofit only
//           apiClient.getWeatherForecast().enqueue(object : Callback<WeatherForecastResponse>{
//                      }
//                override fun onResponse(
//                    call: Call<WeatherForecastResponse>,
//                    response: Response<WeatherForecastResponse>
//                )
//                {
//                    if(response.isSuccessful){
//                        val message1 =  response.body()?.temperature
//                        val message2 = response.body()?.wind
//                        val message3 =  response.body()?.description
//                        textWeatherTemperature.text = "Temperature is ${message1}"
//                        textWeatherWind.text = "Wind is ${message2}"
//                        textWeatherDescription.text = "Ii general, ${message3}"
//                    }
//                }
//
//                override fun onFailure(call: Call<WeatherForecastResponse>, t: Throwable) {
//                    Toast.makeText(this@MainActivity, "Error ${t.message}", Toast.LENGTH_LONG).show()
//                }
//
//            })
        }
    }
}

data class WeatherForecastResponse(val temperature: String, val wind: String, val description: String, val forecast: Array<ForecastPerDay>)
data class ForecastPerDay(
    val day:String,
    val temperature:String,
    val wind:String
)

data class DisplayWeatherToday(val temperature:String, val wind:String, val description:String)

fun mapToDisplayItem(response: WeatherForecastResponse):DisplayWeatherToday{
    val temperature = "Temperature is ${response.temperature}"
    val wind = "Wind is ${response.wind}"
    val description = "In general, ${response.description} "
    return DisplayWeatherToday(temperature, wind, description)
}