package com.coclearapp.pdm_project.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Exercise
import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_questions_container.view.*

class ExercisesFragment(private val exercise: Exercise) : Fragment() {

    private lateinit var model: LevelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(LevelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_questions_container, container, false).apply {


            tv_question.text = "${tv_question.text} ${exercise.Number.toString()}"
            op_a.text = exercise.Optiona
            op_b.text = exercise.Optionb
            op_c.text = exercise.Optionc
        }




        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(exercise: Exercise) = ExercisesFragment(exercise)

    }
}