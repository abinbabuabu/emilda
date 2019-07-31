package com.emilda.emildaapp.MainActivities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.emilda.emildaapp.R
import kotlinx.android.synthetic.main.activity_after_payment.*

class AfterPayment : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_payment)
        val completion = intent?.extras?.getInt("status")
        if (completion == 0) {
            Glide.with(this).load(R.drawable.ic_payment_error).into(completionImage)
            text1_after_pay.text = "Oops !"
            text1_after_pay.setTextColor(Color.RED)
            text2_after_pay.text = "An error has occurred.\n" +
                    "Please try again after a while."
            text2_after_pay.setTextColor(Color.BLACK)
        }

        else {
            Glide.with(this).load(R.drawable.ic_payment_success).into(completionImage)
            text1_after_pay.text = "Your wallet has been successfully"
            text1_after_pay.setTextColor(Color.BLACK)
            text2_after_pay.text = " Recharged"
            text2_after_pay.setTextColor(Color.GREEN)
        }
        Handler().postDelayed({
            val intent = Intent(this,DetailsActivity::class.java)
            startActivity(intent)
        },1000)
    }

    override fun onBackPressed() {
       val intent = Intent(this,DetailsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
