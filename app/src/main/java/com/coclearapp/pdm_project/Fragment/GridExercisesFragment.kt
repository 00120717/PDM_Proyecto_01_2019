package com.coclearapp.pdm_project.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.Activities.LevelsActivity
import com.coclearapp.pdm_project.Adapters.LettersAdapter
import com.coclearapp.pdm_project.Adapters.PatientAdapter

import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Patient
import com.coclearapp.pdm_project.Room.Entity.Sound
import kotlinx.android.synthetic.main.activity_patients.*
import kotlinx.android.synthetic.main.fragment_grid_exercises.view.*

class GridExercisesFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_grid_exercises, container, false)
        // Inflate the layout for this fragment //apply para agregar funcionamiento al fragmento

        initRecycler(view)

        return view
    }

    private fun initRecycler(view: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerview  = view.rview
        val lettersAdapter =


        recyclerview.apply {

            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = LevelsExcersicesFragment()

    }

}