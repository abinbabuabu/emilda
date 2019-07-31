package com.emilda.emildaapp.Viewmodels

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.MultiAutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage

class PrintingViewModel : ViewModel() {

    var Uri: Uri? = null
    var FileName: String? = null
    var FileSize: String? = null
    var UploadProgress: MutableLiveData<Int> = MutableLiveData()
    var StorageUrl:MutableLiveData<Uri> = MutableLiveData()

    val FireStorage = FirebaseStorage.getInstance()

    fun UploadPdf(context: Context) {
        if (Uri != null) {
            FireStorage.getReference("$FileName").putFile(Uri!!)
                .addOnCompleteListener {
                }
                .addOnProgressListener {
                    val value = (it.bytesTransferred.div(it.totalByteCount) * 100)
                    UploadProgress.postValue(value.toInt())
                }
                .addOnCanceledListener {

                }
                .addOnSuccessListener { task ->
                    task.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                        StorageUrl.postValue(it)
                    }
                    Log.d("xy", StorageUrl.toString())
                }


        }

    }


}