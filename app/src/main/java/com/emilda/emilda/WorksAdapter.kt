package com.emilda.emilda

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class WorksAdapter(val height: Int) : RecyclerView.Adapter<WorksAdapter.mViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        context = parent.context
        return mViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_row, parent, false))
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when (position) {
                0 -> {
                    holder.cardImage.setImageDrawable(context.getDrawable(R.drawable.ic_web_design))
                    holder.cardtext.text = "Web Development"
                }
                1 -> {
                    holder.cardImage.setImageDrawable(context.getDrawable(R.drawable.ic_app))
                    holder.cardtext.text = "App Development"
                }
                2 -> {
                    holder.cardImage.setImageDrawable(context.getDrawable(R.drawable.ic_computer))
                    holder.cardtext.text = "Graphic Design"
                }
                3 -> {
                    holder.cardImage.setImageDrawable(context.getDrawable(R.drawable.ic_printer))
                    holder.cardtext.text = "Printing"
                }
                4 -> {
                    holder.cardImage.setImageDrawable(context.getDrawable(R.drawable.ic_email))
                    holder.cardtext.text = "Digital Marketing"
                }
                5 -> {
                    holder.cardImage.setImageDrawable(context.getDrawable(R.drawable.ic_tools))
                    holder.cardtext.text = "Desktop Support"
                }
            }
        } else {
            when (position) {
                0 -> {
                    holder.cardImage.background = context.resources.getDrawable(R.drawable.ic_web_design)
                    holder.cardtext.text = "Web Development"
                }
                1 -> {
                    holder.cardImage.background = context.resources.getDrawable(R.drawable.ic_app)
                    holder.cardtext.text = "App Development"
                }
                2 -> {
                    holder.cardImage.background = context.resources.getDrawable(R.drawable.ic_computer)
                    holder.cardtext.text = "Graphic Design"
                }
                3 -> {
                    holder.cardImage.background = context.resources.getDrawable(R.drawable.ic_printer)
                    holder.cardtext.text = "Printing"
                }
                4 -> {
                    holder.cardImage.background = context.resources.getDrawable(R.drawable.ic_email)
                    holder.cardtext.text = "Digital Marketing"
                }
                5 -> {
                    holder.cardImage.background = context.resources.getDrawable(R.drawable.ic_tools)
                    holder.cardtext.text = "Desktop Support"
                }
            }
        }

        holder.mOnBind(position)
        val height = height
        holder.cardImage.layoutParams.height = height / 8

    }

    inner class mViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val card: CardView = v.findViewById(R.id.services_card)
        val cardImage: ImageView = v.findViewById(R.id.card_image)
        val cardtext: TextView = v.findViewById(R.id.card_text)


        fun mOnBind(position: Int) {
            when(position){
                3 ->{
                    card.setOnClickListener {
                        val intent = Intent(context,PrintingActivity::class.java)
                        startActivity(context, intent, null)
                    }
                }
                else ->{
                    card.setOnClickListener {
                        val intent = Intent(context, ExpandedCard::class.java)
                        startActivity(context, intent, null)
                    }
                }
            }

        }

    }

}