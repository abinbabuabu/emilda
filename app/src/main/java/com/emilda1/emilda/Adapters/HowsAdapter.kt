package com.emilda1.emilda.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emilda1.emilda.R

class HowsAdapter(list1: List<String>, list2: List<String>, iconList: List<Int>) :
    RecyclerView.Adapter<HowsAdapter.howViewholder>() {
    val list1: List<String> = list1
    val list2: List<String> = list2
    val iconList: List<Int> = iconList
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): howViewholder {
        context = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.how_row, parent, false)
        return howViewholder(v)
    }

    override fun getItemCount(): Int {
        return list1.size
    }

    override fun onBindViewHolder(holder: howViewholder, position: Int) {
        holder.text1.text = list1[position]
        holder.text2.text = list2[position]
        Glide.with(context).load(iconList[position]).into(holder.icon)
    }

    inner class howViewholder(v: View) : RecyclerView.ViewHolder(v) {
        val text1: TextView = v.findViewById(R.id.text1_how)
        val text2: TextView = v.findViewById(R.id.text2_how)
        val icon: ImageView = v.findViewById(R.id.icon_how)

    }
}