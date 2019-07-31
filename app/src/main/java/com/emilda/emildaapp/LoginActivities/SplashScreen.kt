package com.emilda.emildaapp.LoginActivities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.emilda.emildaapp.MainActivities.DetailsActivity
import com.emilda.emildaapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*

class SplashScreen : AppCompatActivity() {
    private var mfirebaseAuth: FirebaseAuth? = null
    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mfirebaseAuth = FirebaseAuth.getInstance()

        Glide.with(this)
            .asGif()
            .load(R.drawable.splash_logo)
            .into(splash_gif)

        Handler().postDelayed({
            authentication()
        }, 3400)


    }

    override fun onPause() {
        super.onPause()
        if (mAuthStateListener != null)
            mfirebaseAuth?.removeAuthStateListener(mAuthStateListener!!)
    }

    override fun onResume() {
        super.onResume()
        if (mAuthStateListener != null)
            mfirebaseAuth?.addAuthStateListener(mAuthStateListener!!)

    }

    private fun authentication() {

        mAuthStateListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if (user != null) {
                startActivity(Intent(this, DetailsActivity::class.java))
                finish()
                startActivity(intent)
                finish()
            } else {

                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            }

        }
        mfirebaseAuth?.addAuthStateListener(mAuthStateListener!!)
    }

}
