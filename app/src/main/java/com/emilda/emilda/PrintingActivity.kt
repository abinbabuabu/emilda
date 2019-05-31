package com.emilda.emilda


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.AutoCompleteTextView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.printing_layout.*


class PrintingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_printing)
        setDropDownmenus()

    }

    fun setDropDownmenus() {
        val paperSize = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        val adapter = ArrayAdapter(
            this,
            R.layout.drop_down_menu_item,
            paperSize
        )

        val paperSizeDropDown = findViewById<AutoCompleteTextView>(R.id.dropdown_paper_size)
        paperSizeDropDown.setAdapter(adapter)
        paperSize_drop.hint = "completed"

    }
}


