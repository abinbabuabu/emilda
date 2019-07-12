package com.emilda1.emilda.MainActivities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.emilda1.emilda.R
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.app_bar_details.*
import setUpBottomAppBarShapeAppearance
import java.net.URLEncoder


class DetailsActivity : AppCompatActivity(), PaymentResultListener {

    private val REQUEST_CODE_DOC: Int = 34

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_details)
        exitEnterAnim()

        setUpBottomAppBarShapeAppearance(fab_details, bottom_bar)

        fab_details.setColorFilter(Color.WHITE)
        fab_details.setOnClickListener {
            whatsApp()
        }


        val controllerNav = findNavController(R.id.main_nav_fragment)

        controllerNav.addOnDestinationChangedListener { controller, destination, arguments ->
            controller.popBackStack()
        }

        bottom_navigation.setupWithNavController(controllerNav)


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

    override fun onPaymentError(p0: Int, p1: String?) {
        try {
            val intent = Intent(this, AfterPayment::class.java).apply {
                putExtra("status", 0)
            }
            startActivity(intent)
        } catch (e: Exception) {

        }

    }

    override fun onPaymentSuccess(p0: String?) {
        val intent = Intent(this, AfterPayment::class.java).apply {
            putExtra("status", 1)
        }
        startActivity(intent)
    }


}
