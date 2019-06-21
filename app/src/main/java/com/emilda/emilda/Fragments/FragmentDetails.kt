package com.emilda.emilda.Fragments


import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emilda.emilda.Adapters.PortfolioAdapter
import com.emilda.emilda.Adapters.WorksAdapter
import com.emilda.emilda.Dataclass.portfolio
import com.emilda.emilda.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.content_details.*

class FragmentDetails : Fragment() {

    lateinit var AllAdapter: PortfolioAdapter
    lateinit var DevAdapter: PortfolioAdapter
    lateinit var DesignAdapter: PortfolioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val point = Point()
        val size = activity?.windowManager?.defaultDisplay?.getSize(point)
        card_recycle_view.overScrollMode = View.OVER_SCROLL_NEVER
        card_recycle_view.layoutManager = GridLayoutManager(context, 2)
        card_recycle_view.adapter = WorksAdapter(point.y)

        black_bg.layoutParams.height = point.y / 3

        portfolio_recycle.layoutManager = GridLayoutManager(context, 2)

        setPortfolioFragment(portfolio_recycle)
    }

    fun setPortfolioFragment(recyclerView: RecyclerView) {
        val firebase = FirebaseDatabase.getInstance().reference.child("portfolio")
        val All = firebase.child("all")
        val alloptions = FirebaseRecyclerOptions.Builder<portfolio>().setQuery(All) { snapshot ->
            snapshot.getValue(portfolio::class.java)!!
        }.build()
        val dev = firebase.child("development")
        val devoptions = FirebaseRecyclerOptions.Builder<portfolio>().setQuery(dev) { snapshot ->
            snapshot.getValue(portfolio::class.java)!!
        }.build()
        val design = firebase.child("design")
        val designoptions = FirebaseRecyclerOptions.Builder<portfolio>().setQuery(design) { snapshot ->
            snapshot.getValue(portfolio::class.java)!!
        }.build()
        AllAdapter = PortfolioAdapter(alloptions)
        DesignAdapter = PortfolioAdapter(devoptions)
        DevAdapter = PortfolioAdapter(designoptions)

        recyclerView.adapter = AllAdapter

        all_button.setOnClickListener {
            recyclerView.adapter = AllAdapter
        }
        dev_button.setOnClickListener {
            recyclerView.adapter = DevAdapter
        }
        design_button.setOnClickListener {
            recyclerView.adapter = DesignAdapter
        }

    }

    override fun onStart() {
        super.onStart()
        AllAdapter.startListening()
        DesignAdapter.startListening()
        DevAdapter.startListening()
    }

    override fun onStop() {
        AllAdapter.stopListening()
        DesignAdapter.stopListening()
        DevAdapter.stopListening()
        super.onStop()
    }


}
