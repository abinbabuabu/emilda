package com.emilda.emilda.Dataclass

import androidx.recyclerview.widget.DiffUtil

data class portfolio(var imgurl: String, var title: String, var url: String) {
    constructor() : this("", "", "")

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<portfolio>() {
            override fun areItemsTheSame(oldItem: portfolio, newItem: portfolio): Boolean =
                oldItem.imgurl == newItem.imgurl

            override fun areContentsTheSame(oldItem: portfolio, newItem: portfolio): Boolean =
                oldItem.url == newItem.url
        }

    }
}