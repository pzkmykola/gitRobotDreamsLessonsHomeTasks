package com.example.gitrootdreamslessonshometasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
        manager = supportFragmentManager
        manager.beginTransaction()
            .add(com.google.android.material.R.id.container, FirstFragment() )
            .commit()
        GlobalScope.launch(Dispatchers.Main) {
            val secondFragment = SecondFragment()
            delay(3100)
            val manager = supportFragmentManager
            manager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_left)
                .add(com.google.android.material.R.id.container, secondFragment)
                .commit()
        }
    }
}