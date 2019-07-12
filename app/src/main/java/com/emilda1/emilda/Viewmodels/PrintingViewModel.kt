package com.emilda1.emilda.Viewmodels

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage

class PrintingViewModel : ViewModel() {

    var Uri: Uri? = null
    var FileName: String? = null
    var FileSize: String? = null
    var StorageUrl: String? = null

    val FireStorage = FirebaseStorage.getInstance().getReference("Pdf Uploads")

    fun UploadPdf(context: Context) {
        if (Uri != null) {
            FireStorage.putFile(Uri!!)
                .addOnCompleteListener {

                    Toast.makeText(context, "Finished", Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener {
                    Log.d("xz", (it.bytesTransferred/it.totalByteCount *100).toString())
                }
                .addOnCanceledListener {
                    Log.d("xz", "Its Cancelled")
                }


        }

    }


}