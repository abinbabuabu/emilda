package com.emilda.emildaapp.Viewmodels

import androidx.lifecycle.ViewModel
import com.emilda.emildaapp.Dataclass.SupportType
import com.google.firebase.auth.FirebaseAuth

class DesktopSupportViewModel : ViewModel() {
    var FixType: String =  ""
    var BrandName:String = ""
    var ModelName:String = ""
    var ProblemDesc:String = ""
    var DeliveryLocation:String =""
    var Date:String =""
    var Time:String =""

    val FirebaseDatabase = com.google.firebase.database.FirebaseDatabase.getInstance().reference.child("Support Requests")


    fun UploadData(){
        val Userid = FirebaseAuth.getInstance().currentUser?.uid ?: "null"
        val Data = SupportType(Userid,FixType,BrandName,ModelName,ProblemDesc,DeliveryLocation,Date,Time)
        val key = FirebaseDatabase.push().key
        FirebaseDatabase.child(key!!).setValue(Data)
    }

}