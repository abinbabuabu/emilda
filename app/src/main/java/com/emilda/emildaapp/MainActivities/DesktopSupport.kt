package com.emilda.emildaapp.MainActivities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.emilda.emildaapp.Adapters.SupportViewPagerAdapter
import com.emilda.emildaapp.R
import kotlinx.android.synthetic.main.activity_desktop_support.*

class DesktopSupport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desktop_support)
        val Adapter = SupportViewPagerAdapter(supportFragmentManager)
        val toolbar: Toolbar = this.findViewById(R.id.desk_support_tool)
        setSupportActionBar(toolbar)



        findNavController(R.id.support_fragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.desktopSupport1 -> pod_Slider.currentStep = 0
                R.id.desktopSupport2 -> pod_Slider.currentStep = 1
            }
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
