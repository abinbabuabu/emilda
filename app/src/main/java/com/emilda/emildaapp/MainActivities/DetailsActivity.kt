package com.emilda.emildaapp.MainActivities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.util.Log
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.DetailsViewModel
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.app_bar_details.*
import kotlinx.android.synthetic.main.fragment_recharge.*
import org.jetbrains.anko.contentView
import setUpBottomAppBarShapeAppearance
import java.net.URLEncoder


class DetailsActivity : AppCompatActivity(), PaymentResultListener {
    lateinit var ViewModel: DetailsViewModel
    lateinit var controllerNav: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_details)
        exitEnterAnim()

        setUpBottomAppBarShapeAppearance(fab_details, bottom_bar)

        ViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        ViewModel.getAvailableCredit()

        fab_details.setColorFilter(Color.WHITE)
        fab_details.setOnClickListener {
            whatsApp()
        }


        controllerNav = findNavController(R.id.main_nav_fragment)

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
            Log.d("xy", "Error")
        }
    }


    override fun onPaymentSuccess(p0: String?) {
        val dialog = setDialog()
        ViewModel.updateCredit().addOnCompleteListener {
            dialog.dismiss()
            val intent = Intent(this, AfterPayment::class.java).apply {
                putExtra("status", 1)
            }
            startActivity(intent)
            finish()
        }


    }

    private fun setDialog(): Dialog {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.fragment_loading)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.show()
        return dialog
    }




}
