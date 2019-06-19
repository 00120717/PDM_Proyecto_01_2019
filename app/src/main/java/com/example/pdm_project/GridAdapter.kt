package com.example.pdm_project

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

 class GridAdapter(private val place: IntArray, private val name:Array<String>, private val context: Context)
    :RecyclerView.Adapter<GridHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.fragment_grid_exercises,parent,false)
        return GridHolder(view,context)
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        holder?.index(place[position],name[position])
    }

    override fun getItemCount(): Int {
        return place.size
    }
}