package com.emilda1.emilda.LoginActivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emilda1.emilda.Adapters.mPagerAdapter
import com.emilda1.emilda.R
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        val adapter = mPagerAdapter(supportFragmentManager)
        on_viewPager.adapter = adapter
        dots_indicator.setViewPager(on_viewPager)
    }
}
