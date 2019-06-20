package com.coclearapp.pdm_project.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.Activities.LevelsActivity
import com.coclearapp.pdm_project.Adapters.PatientAdapter
import com.coclearapp.pdm_project.Models.Patient

import com.coclearapp.pdm_project.R

import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import kotlinx.android.synthetic.main.activity_patients.*
import kotlinx.android.synthetic.main.fragment_grid_exercises.*

class GridExercisesFragment(level: Int): Fragment(){


    private lateinit var viewAdapter: PatientAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(LevelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }


    fun initRecycle(patients : List<Patient>){

    }



    private fun patientItemClicked(item: Patient){

        startActivity(Intent(this.context, LevelsActivity::class.java).putExtra("name",item.name))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_grid_exercises, container, false)

        rview.apply {
            layoutManager = LinearLayoutManager(this.context)

        }



        return view


        }



    companion object {
        @JvmStatic
        fun newInstance(n: Int) = GridExercisesFragment(n)

    }
    private lateinit var model: LevelViewModel
    }
