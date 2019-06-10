package com.emilda.emilda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_phone_login.*

class PhoneLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_login)

        next_btn_login.setOnClickListener {
            val intent = Intent(this,OtpScreen::class.java)
            startActivity(intent)
        }
    }
}
