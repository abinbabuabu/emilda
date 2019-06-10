package com.emilda.emilda

import ResolveUrl
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.emilda.emilda.Utils.FirebaseQueryLiveData
import com.google.firebase.database.FirebaseDatabase

class ExpandedCardViewModel : ViewModel() {
    private val Firebase = FirebaseDatabase.getInstance().reference
    private val FirebaseLive = FirebaseQueryLiveData(Firebase)

    fun getPortfolio(position: Int): LiveData<List<String?>> {
        val urlExtension = ResolveUrl(position)
        return Transformations.map(FirebaseLive) {
            it.children.map { child ->
                child.getValue(String::class.java)
            }
        }
    }

}