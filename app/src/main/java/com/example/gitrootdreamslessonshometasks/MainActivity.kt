package com.example.gitrootdreamslessonshometasks

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
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
            intent.putExtra(MESSAGE_TO_SECOND_ACTIVITY, "Message from first activity")
            /*val bundle = Bundle()
            bundle.putParcelable(PARCELABLE_MESSAGE, CustomMessage("custom message"))*/
            startActivityForResult(intent, 600)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(requestCode == 600 && resultCode == RESULT_OK){
            val message = intent?.getStringExtra(SecondActivity.MESSAGE_TO_FIRST_ACTIVITY)
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
    companion object{
        const val MESSAGE_TO_SECOND_ACTIVITY = "MessageToSecondActivity"
        const val PARCELABLE_MESSAGE = "ParcelableMessage"
    }
}

