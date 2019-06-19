package com.coclearapp.pdm_project.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.fragment_levels_excersices.view.*
import kotlinx.android.synthetic.main.fragment_levels_sounds.*

class LevelsExcersicesFragment: Fragment(){

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
        return inflater.inflate(R.layout.fragment_levels_excersices, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LevelsExcersicesFragment()

    }

    private lateinit var model: LevelViewModel
}