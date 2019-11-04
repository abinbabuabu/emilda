package com.emilda.emildaapp.MainActivities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.PrintingViewModel
import kotlinx.android.synthetic.main.printing_layout.*


class PrintingActivity : AppCompatActivity() {
    private val REQUEST_CODE_DOC: Int = 343
    lateinit var printViewModel: PrintingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_printing)
       // val toolbar: Toolbar = this.findViewById(R.id.toolbar_print)

       // setSupportActionBar(toolbar)
        //supportActionBar?.title = "        "
       // supportActionBar?.setDisplayShowTitleEnabled(true)

        printViewModel = ViewModelProviders.of(this).get(PrintingViewModel::class.java)

        findNavController(R.id.printing_fragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.printing1 -> pod_Slider.currentStep = 0
                R.id.printing1 -> pod_Slider.currentStep = 1
                R.id.uploadingStatus -> {
                    pod_Slider.visibility = View.GONE
                    app_bar_layout.visibility = View.GONE
                }
            }
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DOC && resultCode == Activity.RESULT_OK) {
            data?.data?.let { returnUri ->
                printViewModel.Uri = returnUri
                contentResolver.query(returnUri, null, null, null, null)
            }?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                val FileName = cursor.getString(nameIndex)
                val FileSize = cursor.getLong(sizeIndex).toString()

                printViewModel.FileName = FileName
                printViewModel.FileSize = FileSize
                cursor.close()

            }
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}



