package com.example.gitrootdreamslessonshometasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
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

        buttonWeatherForecast.setOnClickListener {
            val client = ApiClient.client.create(ApiInterface::class.java)
            client.getWeatherForecast().enqueue(object : Callback<WeatherForecastResponse>{
                override fun onResponse(
                    call: Call<WeatherForecastResponse>,
                    response: Response<WeatherForecastResponse>
                )
                {
                    if(response.isSuccessful){
                        val message1 =  response.body()?.temperature
                        val message2 = response.body()?.wind
                        val message3 =  response.body()?.description
                        textWeatherTemperature.text = "Temperature is ${message1}"
                        textWeatherWind.text = "Wind is ${message2}"
                        textWeatherDescription.text = "Ii general, ${message3}"
                    }
                }

                override fun onFailure(call: Call<WeatherForecastResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error ${t.message}", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}

data class WeatherForecastResponse(val temperature: String, val wind: String, val description: String, val forecast: Array<ForecastPerDay>)
data class ForecastPerDay(
    val day:String,
    val temperature:String,
    val wind:String
)