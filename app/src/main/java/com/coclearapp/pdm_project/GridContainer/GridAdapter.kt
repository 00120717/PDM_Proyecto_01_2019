package com.coclearapp.pdm_project.GridContainer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.R

class GridAdapter(private val place: IntArray, private val name:Array<String>, private val context: Context)
    :RecyclerView.Adapter<GridHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.fragment_grid_exercises,parent,false)
        return GridHolder(view, context)
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        holder?.index(place[position],name[position])
    }

    override fun getItemCount(): Int {
        return place.size
    }
}