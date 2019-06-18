package com.emilda.emilda.MainActivities

import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emilda.emilda.R
import com.emilda.emilda.Adapters.WorksAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.app_bar_details.*
import kotlinx.android.synthetic.main.content_details.*
import setUpBottomAppBarShapeAppearance
import java.net.URLEncoder


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        exitEnterAnim()

        val point = Point()
        val size = windowManager.defaultDisplay.getSize(point)
        card_recycle_view.overScrollMode = View.OVER_SCROLL_NEVER
        card_recycle_view.layoutManager = GridLayoutManager(this, 2)
        card_recycle_view.adapter = WorksAdapter(point.y)

        black_bg.layoutParams.height = point.y/3

        setUpBottomAppBarShapeAppearance(fab_details, bottom_bar)
        bottom_msg.setOnClickListener {
            whatsApp()
        }

        setPortfolioFragment()

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


    fun setPortfolioFragment() {
        val navController = Navigation.findNavController(this, R.id.portfolio_nav_frag)
        all_button.setOnClickListener {
            navController.popBackStack()
            navController.navigate(R.id.allPorfolio)


        }
        design_button.setOnClickListener {
            navController.popBackStack()
            navController.navigate(R.id.designFragment)

        }
        dev_button.setOnClickListener {
            navController.popBackStack()
            navController.navigate(R.id.devFragment)

        }
    }
}
