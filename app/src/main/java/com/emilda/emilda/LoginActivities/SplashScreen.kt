package com.emilda.emilda.LoginActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.emilda.emilda.MainActivities.DetailsActivity
import com.emilda.emilda.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Glide.with(this)
            .asGif()
            .load(R.drawable.splash_logo)
            .into(splash_gif)

        Handler().postDelayed({
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }, 3400)
    }
}
