package com.emilda.emilda.MainActivities

import android.graphics.Point
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.emilda.emilda.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val point = Point()
        val size = windowManager.defaultDisplay.getSize(point)
        val location = IntArray(2)
        small_cards.getLocationInWindow(location)


        if (resources.displayMetrics.widthPixels>resources.displayMetrics.heightPixels){
            yellow_bg.layoutParams.height = point.y/2 + 100
        }
        else{
            yellow_bg.layoutParams.height = point.y/3 + 50
        }


    }
}
