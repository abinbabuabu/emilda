package com.emilda.emilda.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emilda.emilda.R

class ListWorksAdapter(list: List<String>):RecyclerView.Adapter<ListWorksAdapter.mViewHolder>(){
    val list:List<String> = list
    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        context = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.types_row, parent, false)
        return mViewHolder(v)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.type.text = list[position]
    }

    inner class mViewHolder(v: View):RecyclerView.ViewHolder(v){
        val type: TextView = v.findViewById(R.id.type_text)
    }
}