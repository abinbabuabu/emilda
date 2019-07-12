package com.emilda1.emilda.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.emilda1.emilda.Dataclass.portfolio
import com.emilda1.emilda.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class PortfolioAdapter(options: FirebaseRecyclerOptions<portfolio>) :
    FirebaseRecyclerAdapter<portfolio, PortfolioAdapter.mViewHolder>(options) {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        context = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.portfolio_items, parent, false)
        return mViewHolder(v)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int, current: portfolio) {
        Glide.with(context).load(current.imgurl).centerCrop().transition(DrawableTransitionOptions.withCrossFade(2000))
            .into(holder.Image)
    }


    inner class mViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var Image: ImageView = v.findViewById(R.id.image_portfolio)
    }
}