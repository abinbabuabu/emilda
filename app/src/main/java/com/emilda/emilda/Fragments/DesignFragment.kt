package com.emilda.emilda.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.emilda.emilda.Adapters.PortfolioAdapter
import com.emilda.emilda.Dataclass.portfolio

import com.emilda.emilda.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_design.*

class DesignFragment : Fragment() {
lateinit var adapter:PortfolioAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_design, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        design_rv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val portfolioUrl = FirebaseDatabase.getInstance().reference.child("portfolio").child("design")
        val options = FirebaseRecyclerOptions.Builder<portfolio>().setQuery(portfolioUrl) {
                snapshot -> snapshot.getValue(portfolio::class.java)!!
        }.build()
        adapter = PortfolioAdapter(options)
        design_rv.adapter = adapter
    }
    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        adapter.stopListening()
        super.onStop()
    }

}
