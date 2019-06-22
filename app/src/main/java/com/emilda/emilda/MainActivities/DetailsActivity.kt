package com.emilda.emilda.MainActivities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.annotation.GlideModule
import com.emilda.emilda.R
import kotlinx.android.synthetic.main.app_bar_details.*
import kotlinx.android.synthetic.main.main.*
import setUpBottomAppBarShapeAppearance
import java.net.URLEncoder


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_details)
        exitEnterAnim()

        setUpBottomAppBarShapeAppearance(fab_details, bottom_bar)

        fab_details.setColorFilter(Color.WHITE)


        val controller = main_nav_fragment.findNavController()
        ic_profile_bottom.setOnClickListener {
            controller.popBackStack()
            controller.navigate(R.id.fragmentProfile)
        }
        ic_home_bottom.setOnClickListener {
            controller.popBackStack()
            controller.navigate(R.id.fragmentDetails)
        }
        ic_recharge_bottom.setOnClickListener {
            controller.popBackStack()
            controller.navigate(R.id.fragmentRecharge)
        }


    }


    fun whatsApp() {
        val packageManager = this.packageManager
        val i = Intent(Intent.ACTION_VIEW)

        try {
            val url = "https://api.whatsapp.com/send?phone=" + "919008517230" + "&text=" + URLEncoder.encode(
                "Hello from Emilda",
                "UTF-8"
            )
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun exitEnterAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val explode = Explode()
            explode.duration = 3000
            window.reenterTransition = explode

        }
    }

}
