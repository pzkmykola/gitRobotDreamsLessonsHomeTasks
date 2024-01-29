package com.example.gitrootdreamslessonshometasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.myPhone)
        textView.setText("  My phone number is +38-067-681-76-51")

//        val buttonPhone: Button = findViewById(R.id.callPhoneButton)
//        buttonPhone.setOnClickListener {
//            val toast = Toast.makeText(this, "My phone number: +38067-681-7651", Toast.LENGTH_LONG)
//            toast.show()
//        }

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
    }
}