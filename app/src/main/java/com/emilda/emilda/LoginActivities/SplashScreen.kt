package com.emilda.emilda.LoginActivities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.emilda.emilda.MainActivities.DetailsActivity
import com.emilda.emilda.R
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
            authen()
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


    private fun authen() {
        mAuthStateListener = FirebaseAuth.AuthStateListener {
            var user = it.currentUser
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
