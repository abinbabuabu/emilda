package com.emilda1.emilda.Viewmodels

import ResolveUrl
import androidx.lifecycle.ViewModel
import com.emilda1.emilda.Dataclass.portfolio
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class ExpandedCardViewModel : ViewModel() {
    private val Firebase = FirebaseDatabase.getInstance().reference.child("portfolio")

    fun getPortfolio(position: Int): FirebaseRecyclerOptions<portfolio> {
        val urlExtension = ResolveUrl(position)
        val portfolioUrl = Firebase.child(urlExtension)
        return FirebaseRecyclerOptions.Builder<portfolio>().setQuery(portfolioUrl) { snapshot ->
            snapshot.getValue(portfolio::class.java)!!
        }.build()
    }
}