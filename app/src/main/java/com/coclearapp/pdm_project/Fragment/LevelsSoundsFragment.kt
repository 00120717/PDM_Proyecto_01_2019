package com.coclearapp.pdm_project.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import com.coclearapp.pdm_project.R

class LevelsSoundsFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_levels_sounds,container,false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LevelsSoundsFragment().apply {

            }

    }

    private lateinit var model: LevelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(LevelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }
}