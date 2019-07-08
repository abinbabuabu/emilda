package com.emilda.emilda.MainActivities


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.emilda.emilda.R
import kotlinx.android.synthetic.main.printing_layout.*
import java.io.File
import java.net.URI
import java.net.URISyntaxException


class PrintingActivity : AppCompatActivity() {
    private val REQUEST_CODE_DOC: Int = 343


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.printing_layout)
        setDropDownMenus()

        findNavController(R.id.printing_fragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DOC)
            if (resultCode == Activity.RESULT_OK) {
                val uri = data?.data
                val fileP = getFileNameByUri(this, uri!!)
                Log.d("xy", fileP.length.toString())

            }
    }


    private fun getFileNameByUri(context: Context, uri: Uri): String {
        var filepath = ""//default fileName
        //Uri filePathUri = uri;
        val file: File
        if (uri.scheme.toString().compareTo("content") === 0) {
            val cursor = context.contentResolver.query(
                uri,
                arrayOf<String>(
                    android.provider.MediaStore.Images.ImageColumns.DATA,
                    MediaStore.Images.Media.ORIENTATION
                ),
                null,
                null,
                null
            )
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

            cursor.moveToFirst()

            val mImagePath = cursor.getString(column_index)
            cursor.close()
            filepath = mImagePath

        } else if (uri.scheme.compareTo("file") === 0) {
            try {
                file = File(URI(uri.toString()))
                if (file.exists())
                    filepath = file.absolutePath

            } catch (e: URISyntaxException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        } else {
            filepath = uri.path
        }
        return filepath
    }
}


