package com.example.gitrootdreamslessonshometasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Guideline
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPhone: Button = findViewById(R.id.callPhoneButton)
        val buttonEmail: Button = findViewById(R.id.sendEmailButton)
        val guideLine: Guideline = findViewById(R.id.guideLine)
        val textView = findViewById<TextView>(R.id.biography)
        textView.setText(R.string.txt_my_bio_content)

        val modifyBackground:ImageView = findViewById(R.id.imageView)
        modifyBackground.setOnClickListener {
            val toast = Toast.makeText(this, "Lviv is the best city of Ukraine!!!", Toast.LENGTH_LONG)
            toast.show()
        }

        GlobalScope.launch(Dispatchers.Main) {
            delay(3100)
            buttonPhone.visibility = View.VISIBLE
            delay(200)
            buttonEmail.visibility = View.VISIBLE

            val springAnimation = SpringAnimation(buttonEmail, DynamicAnimation.X)
            val springForce = SpringForce()
            springForce.finalPosition = (guideLine?.pivotY?: 0.0F)/2
            springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            springForce.stiffness = SpringForce.STIFFNESS_VERY_LOW
            springAnimation.spring = springForce
            springAnimation.start()
        }

        buttonPhone.setOnClickListener {
            val toast = Toast.makeText(this, R.string.txt_phone_number_text, Toast.LENGTH_LONG)
            toast.show()
        }

        buttonEmail.setOnClickListener {
            val toast = Toast.makeText(this, R.string.txt_my_email, Toast.LENGTH_LONG)
            toast.show()
        }
    }
}