package com.emilda.emildaapp.Viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetailsViewModel : ViewModel() {
    var amountSelected: Int = 0
    var CurrentUser = FirebaseAuth.getInstance().currentUser
    val AvailableCredits : MutableLiveData<Int> = MutableLiveData()
    private val Firebase = FirebaseDatabase.getInstance().getReference("UsersData").child(CurrentUser!!.uid)

    fun getAvailableCredit() {
        Firebase.child("credit")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                  val value = p0.getValue(Int::class.java)
                    AvailableCredits.postValue(value)
                    Log.d("xy",AvailableCredits.toString())
                }
            })
    }

    fun updateCredit():Task<Void>{
        val finalValue = AvailableCredits.value?.plus(amountSelected) ?: 0
        return Firebase.child("credit").setValue(finalValue)
    }

    fun getName():MutableLiveData<String>{
        val name = MutableLiveData<String>()
        Firebase.child("fullName")
            .addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                   name.postValue("")
                }

                override fun onDataChange(p0: DataSnapshot) {
                   name.postValue(p0.getValue(String::class.java))
                }

            })
        return name
    }

    fun getEmail():MutableLiveData<String>{
        val email = MutableLiveData<String>()
        Firebase.child("email")
            .addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    email.postValue("")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    email.postValue(p0.getValue(String::class.java))
                }

            })
        return email
    }

}