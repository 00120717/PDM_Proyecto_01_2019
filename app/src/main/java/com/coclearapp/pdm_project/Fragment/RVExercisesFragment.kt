package com.coclearapp.pdm_project.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.Activities.LevelsActivity
import com.coclearapp.pdm_project.Adapters.LettersAdapter
import com.coclearapp.pdm_project.Adapters.PatientAdapter
import com.coclearapp.pdm_project.Adapters.QuestionItemAdapter
import com.coclearapp.pdm_project.Models.Question

import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Exercise
import com.coclearapp.pdm_project.Room.Entity.Sound
import com.coclearapp.pdm_project.ViewModel.ExerciseViewModel

import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_grid_exercises.view.*

class RVExercisesFragment(private var level: Int): Fragment(){


    private lateinit var viewAdapter: QuestionItemAdapter
    private lateinit var exerciseViewModel: ExerciseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(LevelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }


    private fun exerciseItemClicked(item: Exercise){
        var fragment = ExercisesFragment.newInstance(item)

        fragmentManager!!
            .beginTransaction()
            .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
            .replace(R.id.fl_content, fragment)
            .commit()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewAdapter = QuestionItemAdapter(emptyList(),{ exerciseitem: Exercise-> exerciseItemClicked(exerciseitem)})

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)

        exerciseViewModel.levelExercise(level).observe(this, Observer {
            viewAdapter.dataChange(it)
        })


        var view = inflater.inflate(R.layout.fragment_grid_exercises, container, false)


        view.close_button.setOnClickListener {
            var fragment = LevelsExcersicesFragment.newInstance()

            fragmentManager!!
                    .beginTransaction()
                    .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                    .replace(R.id.fl_content, fragment)
                    .commit()
        }

        view.rview.apply {
            layoutManager = GridLayoutManager(this.context,2)
            adapter = viewAdapter

        }



        return view


        }



    companion object {
        @JvmStatic
        fun newInstance(n: Int) = RVExercisesFragment(n)

    }
    private lateinit var model: LevelViewModel
    }
