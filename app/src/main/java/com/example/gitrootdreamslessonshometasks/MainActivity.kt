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

        val textView = findViewById<TextView>(R.id.biography)
        textView.setText(R.string.txt_my_bio_content)

        val buttonPhone: Button = findViewById(R.id.callPhoneButton)
        buttonPhone.setOnClickListener {
            val toast = Toast.makeText(this, R.string.txt_phone_number_text, Toast.LENGTH_LONG)
            toast.show()
        }

        val buttonEmail: Button = findViewById(R.id.sendEmailButton)
        buttonEmail.setOnClickListener {
            val toast = Toast.makeText(this, R.string.txt_my_email, Toast.LENGTH_LONG)
            toast.show()
        }

        val modifyBackground:ImageView = findViewById(R.id.imageView)
        modifyBackground.setOnClickListener {
            val toast = Toast.makeText(this, "Lviv is the best city of Ukraine!!!", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}