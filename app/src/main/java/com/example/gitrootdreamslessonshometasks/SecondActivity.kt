package com.example.gitrootdreamslessonshometasks

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val editTextTo: EditText = findViewById<EditText>(R.id.emailAddress)
        val editTextMessage: EditText = findViewById<EditText>(R.id.texMessage)
        val sendButtonTo: Button = findViewById(R.id.buttonSendTo)

        sendButtonTo.setOnClickListener {

            val field_to: String = editTextTo.text.toString()
            val subject: String = ""
            val message: String = editTextMessage.text.toString()
            val email: Intent = Intent(/* action = */ Intent.ACTION_SEND)
            Toast.makeText(this, field_to, Toast.LENGTH_SHORT).show()

            with(email) {
                //Toast.makeText(this, field_to, Toast.LENGTH_SHORT).show()

                putExtra(Intent.EXTRA_EMAIL, new String[] { "mykola.paziuk@intellias.com" })


                //email.putExtra(Intent.EXTRA_EMAIL, field_to)
                email.putExtra(Intent.EXTRA_SUBJECT, subject)
                email.putExtra(Intent.EXTRA_TEXT, message)
                email.setType("message/rfc822")
                startActivity(/* intent = */ Intent.createChooser(
                    email,
                    "Choose an Email client:"
                )
                )
            }
        }
        val intent = Intent()
        intent.putExtra(MESSAGE_TO_FIRST_ACTIVITY, "Email was sent successfully!")
        setResult(RESULT_OK, intent)
        finish()
    }
    companion object{
        const val MESSAGE_TO_FIRST_ACTIVITY = "MessageToFirstActivity"
    }

}