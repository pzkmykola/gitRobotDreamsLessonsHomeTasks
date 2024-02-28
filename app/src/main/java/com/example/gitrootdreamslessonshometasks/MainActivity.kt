package com.example.gitrootdreamslessonshometasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.Guideline
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.FragmentManager
import com.example.gitrootdreamslessonshometasks.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var manager:FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment = FirstFragment()
        manager = supportFragmentManager
        manager.beginTransaction()
            .add(com.google.android.material.R.id.container, firstFragment)
            .commit()

        GlobalScope.launch(Dispatchers.Main) {
            val secondFragment = SecondFragment()
            delay(3100)
            val manager = supportFragmentManager
            manager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_left)
                .add(com.google.android.material.R.id.container, secondFragment)
                .commit()
            val phoneButton = firstFragment.view?.findViewById<Button>(R.id.callPhoneButton)
            val emailButton = firstFragment.view?.findViewById<Button>(R.id.sendEmailButton)

            delay(1600)
            val guideLine = firstFragment.view?.findViewById<Guideline>(R.id.guideLine)
            phoneButton?.visibility = View.VISIBLE
            emailButton?.visibility = View.VISIBLE

            delay(200)
            val springAnimation = SpringAnimation(emailButton, DynamicAnimation.X)
            val springForce = SpringForce()
            springForce.finalPosition = (guideLine?.pivotY?: 0.0F)/2
            springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            springForce.stiffness = SpringForce.STIFFNESS_VERY_LOW
            springAnimation.spring = springForce
            springAnimation.start()
        }
    }
}