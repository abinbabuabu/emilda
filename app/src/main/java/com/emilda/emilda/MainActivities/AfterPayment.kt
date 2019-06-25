package com.emilda.emilda.MainActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.emilda.emilda.R
import kotlinx.android.synthetic.main.activity_after_payment.*

class AfterPayment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_payment)
        val completion = intent?.extras?.getInt("status")
        if(completion == 0)
            Glide.with(this).load(R.drawable.ic_payment_error).into(completionImage)
        else
            Glide.with(this).load(R.drawable.ic_payment_success).into(completionImage)
    }
}
