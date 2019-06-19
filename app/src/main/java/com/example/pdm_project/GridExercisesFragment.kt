package com.example.pdm_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView

class GridExercisesFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment //apply para agregar funcionamiento al fragmento
        return inflater.inflate(R.layout.fragment_grid_exercises, container, false).apply {

            val rview = findViewById<View>(R.id.rview) as RecyclerView
            val place = intArrayOf(1,2,3,4,5)
            val name = arrayOf("A","E","I","O","U")
            val lManager = GridLayoutManager(this@GridExercisesFragment,3,VERTICAL,false)
            rview.layoutManager = lManager

            rview.adapter = GridAdapter(place,name,this@GridExercisesFragment.context!!)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LevelsExcersicesFragment()

    }

}