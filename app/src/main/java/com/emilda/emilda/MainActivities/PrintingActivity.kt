package com.emilda.emilda.MainActivities


import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.emilda.emilda.R
import kotlinx.android.synthetic.main.printing_layout.*


class PrintingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.printing_layout)
        setDropDownMenus()

        findNavController(R.id.printing_fragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.printing1 -> pod_Slider.currentStep = 0
                R.id.printing2 -> pod_Slider.currentStep = 1
            }
        }

    }

    private fun setDropDownMenus() {
        // Paper Size
//        val paperSize = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
//        val adapter = ArrayAdapter(
//            this,
//            R.layout.drop_down_menu_item,
//            paperSize
//        )
//        val paperSizeDropDown = findViewById<AutoCompleteTextView>(R.id.dropdown_paper_size)
//        paperSizeDropDown.setAdapter(adapter)

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


