package com.coclearapp.pdm_project.GridContainer

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GridHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView){
    private val imageview:ImageView
    private val textView:TextView

    init {
        imageview = itemView.findViewById(R.id.iview)
        textView = itemView.findViewById(R.id.tv_item)
    }

    fun index(item:Int, s:String){
        Glide.with(context).load(item).into(imageview)
        textView.text = s
    }
}