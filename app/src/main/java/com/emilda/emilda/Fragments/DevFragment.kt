package com.emilda.emilda.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.emilda.emilda.Adapters.PortfolioAdapter
import com.emilda.emilda.Dataclass.portfolio
import com.emilda.emilda.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_design.*
import kotlinx.android.synthetic.main.fragment_dev.*


class DevFragment : Fragment() {
    lateinit var adapter: PortfolioAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dev, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dev_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val portfolioUrl = FirebaseDatabase.getInstance().reference.child("development")
        val options = FirebaseRecyclerOptions.Builder<portfolio>().setQuery(portfolioUrl) { snapshot ->
            snapshot.getValue(portfolio::class.java)!!
        }.build()
        adapter = PortfolioAdapter(options)
        dev_rv.adapter = adapter
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
