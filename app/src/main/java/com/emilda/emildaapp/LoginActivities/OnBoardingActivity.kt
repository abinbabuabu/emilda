package com.emilda.emildaapp.LoginActivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.emilda.emildaapp.Adapters.mPagerAdapter
import com.emilda.emildaapp.R
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {
    private var Flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        val adapter = mPagerAdapter(supportFragmentManager)
        on_viewPager.adapter = adapter
        dots_indicator.setViewPager(on_viewPager)

        val ButtonAnim = AnimationUtils.loadAnimation(applicationContext,R.anim.button_animation)

       on_viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
           override fun onPageScrollStateChanged(state: Int) {

           }

           override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

           }

           override fun onPageSelected(position: Int) {
             if(position==2 && Flag){
                 Flag = false
                 get_started.animation = ButtonAnim
                 get_started.visibility = View.VISIBLE
             }
           }
       })

        get_started.setOnClickListener {
            val intent = Intent(this, PhoneLogin::class.java)
            startActivity(intent)
            finish()
        }

    }
}
