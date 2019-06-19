package com.emilda.emilda.MainActivities

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.emilda.emilda.R
import kotlinx.android.synthetic.main.main.*
import java.net.URLEncoder


class DetailsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        exitEnterAnim()

        val controller = main_nav_fragment.findNavController()

        controller.navigate()


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


//    fun setPortfolioFragment() {
//        val navController = Navigation.findNavController(this, R.id.portfolio_nav_frag)
//        all_button.setOnClickListener {
//            navController.popBackStack()
//            navController.navigate(R.id.allPorfolio)
//
//
//        }
//        design_button.setOnClickListener {
//            navController.popBackStack()
//            navController.navigate(R.id.designFragment)
//
//        }
//        dev_button.setOnClickListener {
//            navController.popBackStack()
//            navController.navigate(R.id.devFragment)
//
//        }
//    }






}
