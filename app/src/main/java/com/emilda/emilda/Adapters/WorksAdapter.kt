package com.emilda.emilda.Adapters

import ResolveDetails
import ResolvePosition
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emilda.emilda.MainActivities.DesktopSupport
import com.emilda.emilda.MainActivities.ExpandedCard
import com.emilda.emilda.MainActivities.PrintingActivity
import com.emilda.emilda.R

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
        when (position) {
            0 -> {
                Glide.with(context).load(R.drawable.ic_web_dev).into(holder.cardImage)
            }
            1 -> {
                Glide.with(context).load(R.drawable.ic_app_dev).into(holder.cardImage)
            }
            2 -> {
                Glide.with(context).load(R.drawable.ic_graphic_design).into(holder.cardImage)
            }
            3 -> {
                Glide.with(context).load(R.drawable.ic_printing_service).into(holder.cardImage)
            }
            4 -> {
                Glide.with(context).load(R.drawable.ic_digital_marketing).into(holder.cardImage)
            }
            else -> {
                Glide.with(context).load(R.drawable.ic_desktop_support).into(holder.cardImage)
            }
        }
        holder.jobtitle.text = ResolvePosition(position)
        holder.jobdetails.text = ResolveDetails(position)

        holder.mOnBind(position)
        val height = height
        holder.cardImage.layoutParams.height = height / 8

    }

    inner class mViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val card: CardView = v.findViewById(R.id.services_card)
        val cardImage: ImageView = v.findViewById(R.id.card_image)
        val jobtitle: TextView = v.findViewById(R.id.job_title)
        val jobdetails: TextView = v.findViewById(R.id.job_details)

        fun mOnBind(position: Int) {
            when (position) {
                3 -> {
                    card.setOnClickListener {
                        val intent = Intent(context, PrintingActivity::class.java)
                        startActivity(context, intent, null)
                    }
                }
                5 -> {
                    card.setOnClickListener {
                        val intent = Intent(context, DesktopSupport::class.java)
                        startActivity(context, intent, null)
                    }
                }
                else -> {
                    card.setOnClickListener {
                        val intent = Intent(context, ExpandedCard::class.java)
                        intent.putExtra("position", position)
                        startActivity(context, intent, null)
                    }
                }
            }

        }

    }

}