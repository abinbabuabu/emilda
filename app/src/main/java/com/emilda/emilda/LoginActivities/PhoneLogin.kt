package com.emilda.emilda.LoginActivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emilda.emilda.R
import kotlinx.android.synthetic.main.activity_phone_login.*

class PhoneLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_login)

        next_btn_login.setOnClickListener {
            val phone = phone_number.text.toString()
            Log.d("phone", phone)
            if (phone.length != 10) {
                Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, OtpScreen::class.java)
                intent.putExtra("phone", phone)
                startActivity(intent)
            }
        }
    }
}


