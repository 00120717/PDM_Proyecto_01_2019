package com.coclearapp.pdm_project.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.coclearapp.pdm_project.Activities.NewPatientActivity
import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.fragment_levels_excersices.view.*
import kotlinx.android.synthetic.main.fragment_levels_sounds.*

class LevelsExcersicesFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_levels_excersices, container, false).apply {
            ib_excersises_level1.setOnClickListener {

                var fragment = GridExercisesFragment.newInstance(1)

                fragmentManager!!
                        .beginTransaction()
                        .setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_left_in, R.anim.push_left_out)
                        .replace(R.id.fl_content, fragment)
                        .commit()
            }

            ib_excersises_level2.setOnClickListener {

                var fragment = GridExercisesFragment.newInstance(2)

                fragmentManager!!
                        .beginTransaction()
                        .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                        .replace(R.id.fl_content, fragment)
                        .commit()
            }

            ib_excersises_level3.setOnClickListener {

                var fragment = GridExercisesFragment.newInstance(3)

                fragmentManager!!
                        .beginTransaction()
                        .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                        .replace(R.id.fl_content, fragment)
                        .commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                LevelsExcersicesFragment()

    }

    private lateinit var model: LevelViewModel
}