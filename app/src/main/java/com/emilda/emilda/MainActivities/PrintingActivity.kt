package com.emilda.emilda.MainActivities


import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.emilda.emilda.R


class PrintingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_printing)
        val toolbar: Toolbar = findViewById(R.id.printing_toolbar)
        setSupportActionBar(toolbar)
        setDropDownMenus()

    }

    private fun setDropDownMenus() {
        // Paper Size
        val paperSize = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        val adapter = ArrayAdapter(
            this,
            R.layout.drop_down_menu_item,
            paperSize
        )
        val paperSizeDropDown = findViewById<AutoCompleteTextView>(R.id.dropdown_paper_size)
        paperSizeDropDown.setAdapter(adapter)

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


