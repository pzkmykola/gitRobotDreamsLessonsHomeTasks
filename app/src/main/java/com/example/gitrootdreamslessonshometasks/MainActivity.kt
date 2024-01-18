package com.example.gitrootdreamslessonshometasks

import android.content.Intent
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
        textView.setText("  I was born on 12.19.1963 in the city of Khorostkiv, Ternopil region. " +
                " Since I graduated from Lviv State University in 1985, I have been working and living in Lviv (the best city of Ukraine).\n" +
                "  My experience as a C/C++ developer and electronics engineer is more 36 years. "  +
                "I started my professional work as a student (1983–1985. University research sector as a Laboratory assistant. Repairing and maintenance of electronic equipment. Software development.). " +
                "Then I continued a long professional path as an engineer and programmer:\n" +
                " 1985-2003 - R&D Division in Scientific Research Institute of PHTE Lviv\n" +
                " 2005-2011 - PDT Ukraine\n" +
                " 2012-2017 - Cypress Microsystems\n" +
                " 2018-2019 - Ezlo Innovation\n"+
                " 2021-2023 - Intellias.\n" +
                "  However, it's never too late to learn - now I aspire to become an Android developer as well." +
                " I want to start my own business, combined with Home Automation, to pass on to my nephew and my grandson." +
                "\n\n    My emain: mykola.pazuk@gmail.com")


        val buttonPhone: Button = findViewById(R.id.callPhoneButton)
        buttonPhone.setOnClickListener {
            val toast = Toast.makeText(this, "My phone number: +38067-681-7651", Toast.LENGTH_LONG)
            toast.show()
        }

        val modifyBackground:ImageView = findViewById(R.id.imageView)
        modifyBackground.setOnClickListener {
            val toast = Toast.makeText(this, "Lviv is the best city of Ukraine!!!", Toast.LENGTH_LONG)
            toast.show()
        }

        val buttonEmail: Button = findViewById(R.id.sendEmailButton)
        buttonEmail.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}