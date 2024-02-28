package com.example.gitrootdreamslessonshometasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FirstFragment(): Fragment() {
    private lateinit var buttonPhone:Button
    private lateinit var buttonEmail:Button
    private lateinit var modifyBackground:ImageView
    private lateinit var textView:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.first_fragment, container, false)
        buttonPhone = view.findViewById(R.id.callPhoneButton)
        buttonEmail = view.findViewById(R.id.sendEmailButton)
        modifyBackground  = view.findViewById(R.id.imageView)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonPhone.setOnClickListener {
            val toast = Toast.makeText(requireContext(), R.string.txt_phone_number_text, Toast.LENGTH_LONG)
            toast.show()
        }

        buttonEmail.setOnClickListener {
            val toast = Toast.makeText(requireContext(), R.string.txt_my_email, Toast.LENGTH_LONG)
            toast.show()
        }

        modifyBackground.setOnClickListener {
            val toast = Toast.makeText(requireContext(), R.string.txt_my_message, Toast.LENGTH_LONG)
            toast.show()
        }
    }
}