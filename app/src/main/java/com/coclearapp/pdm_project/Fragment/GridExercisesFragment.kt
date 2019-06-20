package com.coclearapp.pdm_project.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.coclearapp.pdm_project.R

import com.coclearapp.pdm_project.ViewModel.LevelViewModel

class GridExercisesFragment(level: Int): Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(LevelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_exercises, container, false)


        }

    companion object {
        @JvmStatic
        fun newInstance(n: Int) = GridExercisesFragment(n)

    }
    private lateinit var model: LevelViewModel
    }
