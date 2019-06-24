package com.coclearapp.pdm_project.Fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Exercise
import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_questions_container.*
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

        view.op_a.setOnClickListener {
            if(validateOptionSelected(op_a.text.toString(),exercise.Answer)){
                op_b.visibility = View.INVISIBLE
                op_c.visibility = View.INVISIBLE

            }
        }

        view.op_b.setOnClickListener {
            if(validateOptionSelected(op_b.text.toString(),exercise.Answer)){
                op_a.visibility = View.INVISIBLE
                op_c.visibility = View.INVISIBLE

            }
        }

        view.op_c.setOnClickListener {
            if(validateOptionSelected(op_c.text.toString(),exercise.Answer)){
                op_b.visibility = View.INVISIBLE
                op_a.visibility = View.INVISIBLE

            }
        }

        view.im_sound.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, exercise.Sound)
            mediaPlayer?.start()
        }


        return view
    }


    fun validateOptionSelected(select: String,ans: String ): Boolean{

        if (select == ans){
            return true
        }
        return false
    }

    companion object {
        @JvmStatic
        fun newInstance(exercise: Exercise) = ExercisesFragment(exercise)

    }
}