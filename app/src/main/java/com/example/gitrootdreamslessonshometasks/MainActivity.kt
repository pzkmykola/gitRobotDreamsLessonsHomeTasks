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

    lateinit var binding:ActivityMainBinding
    @Inject
    lateinit var viewModel:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.getData()
        }
        viewModel.uiState.observe(this){
            when(it){
                is MyViewModel.UIState.Empty -> Unit
                is MyViewModel.UIState.Result -> {
                    binding.progressBar.visibility = INVISIBLE
                    binding.textView.text = it.title
                }
                is MyViewModel.UIState.Processing -> binding.progressBar.visibility = VISIBLE
                is MyViewModel.UIState.Error -> {
                    binding.progressBar.visibility = INVISIBLE
                    binding.textView.text = it.description
                }
            }
        }
    }
}
