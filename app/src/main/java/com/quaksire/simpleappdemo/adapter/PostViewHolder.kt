package com.quaksire.simpleappdemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.quaksire.simpleappdemo.R

/**
 * Created by Julio.
 */
class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val view: View = itemView
    val title: TextView
    val id: TextView

    init {
        title = view.findViewById(R.id.title)
        id = view.findViewById(R.id.id)
    }

}