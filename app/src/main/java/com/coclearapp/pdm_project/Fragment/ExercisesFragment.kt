package com.coclearapp.pdm_project.Fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Exercise
import com.coclearapp.pdm_project.ViewModel.ExerciseViewModel
import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_questions_container.*
import kotlinx.android.synthetic.main.fragment_questions_container.view.*

class ExercisesFragment(private val exercise: Exercise) : Fragment() {

    private lateinit var model: LevelViewModel
    private lateinit var exerciseViewModel: ExerciseViewModel

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

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)

        view.op_a.setOnClickListener {
            if(validateOptionSelected(op_a.text.toString(),exercise.Answer)){
                op_a.visibility = View.INVISIBLE
                op_b.visibility = View.INVISIBLE
                op_c.visibility = View.INVISIBLE
                view.tv_correct.setText(R.string.correcto_string)
                tv_correct.visibility = View.VISIBLE
                view.im_sound.setBackgroundResource(R.drawable.ic_chevron_right_black_24dp)
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.aplausos_v2)
                mediaPlayer?.start()

                exerciseViewModel.getOneByLevelAndNumber(exercise.Level,exercise.Number+1).observe(this, Observer {exer ->

                    if(exer != null){
                        view.im_sound.setOnClickListener {

                            var fragment = newInstance(exer)

                            fragmentManager!!
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                                    .replace(R.id.fl_content, fragment)
                                    .commit()

                        }
                    }else{
                        view.tv_correct.setText(R.string.feliz_string)
                        view.im_sound.setOnClickListener {

                            var fragment = LevelsExcersicesFragment.newInstance()

                            fragmentManager!!
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                                    .replace(R.id.fl_content, fragment)
                                    .commit()

                        }

                    }



                })



            }else{
                view.op_a.setBackgroundResource(R.drawable.circular_textview_wrong)
                view.op_a.setTextColor(getResources().getColor(R.color.cirular_textview_background))
                view.op_a.isEnabled = false
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.incorrecto)
                mediaPlayer?.start()

            }
        }

        view.op_b.setOnClickListener {
            if(validateOptionSelected(op_b.text.toString(),exercise.Answer)){
                op_a.visibility = View.INVISIBLE
                op_b.visibility = View.INVISIBLE
                op_c.visibility = View.INVISIBLE
                view.tv_correct.setText(R.string.correcto_string)
                tv_correct.visibility = View.VISIBLE
                view.im_sound.setBackgroundResource(R.drawable.ic_chevron_right_black_24dp)
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.aplausos_v2)
                mediaPlayer?.start()

                exerciseViewModel.getOneByLevelAndNumber(exercise.Level,exercise.Number+1).observe(this, Observer {exer ->

                    if(exer != null){
                        view.im_sound.setOnClickListener {

                            var fragment = newInstance(exer)

                            fragmentManager!!
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                                    .replace(R.id.fl_content, fragment)
                                    .commit()

                        }
                    }else{
                        view.tv_correct.setText(R.string.feliz_string)
                        view.im_sound.setOnClickListener {

                            var fragment = LevelsExcersicesFragment.newInstance()

                            fragmentManager!!
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                                    .replace(R.id.fl_content, fragment)
                                    .commit()

                        }
                    }
                })
            }else{
                view.op_b.setBackgroundResource(R.drawable.circular_textview_wrong)
                view.op_b.setTextColor(getResources().getColor(R.color.cirular_textview_background))
                view.op_b.isEnabled = false
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.incorrecto)
                mediaPlayer?.start()

            }
        }

        view.op_c.setOnClickListener {
            if(validateOptionSelected(op_c.text.toString(),exercise.Answer)){
                op_a.visibility = View.INVISIBLE
                op_b.visibility = View.INVISIBLE
                op_c.visibility = View.INVISIBLE
                view.tv_correct.setText(R.string.correcto_string)
                tv_correct.visibility = View.VISIBLE
                view.im_sound.setBackgroundResource(R.drawable.ic_chevron_right_black_24dp)
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.aplausos_v2)
                mediaPlayer?.start()


                exerciseViewModel.getOneByLevelAndNumber(exercise.Level,exercise.Number+1).observe(this, Observer {exer ->

                    if(exer != null){
                        view.im_sound.setOnClickListener {

                            var fragment = newInstance(exer)

                            fragmentManager!!
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                                    .replace(R.id.fl_content, fragment)
                                    .commit()

                        }
                    }else{
                        view.tv_correct.setText(R.string.feliz_string)
                        view.im_sound.setOnClickListener {

                            var fragment = LevelsExcersicesFragment.newInstance()

                            fragmentManager!!
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                                    .replace(R.id.fl_content, fragment)
                                    .commit()

                        }
                    }
                })

            }else{
                view.op_c.setBackgroundResource(R.drawable.circular_textview_wrong)
                view.op_c.setTextColor(getResources().getColor(R.color.cirular_textview_background))
                view.op_c.isEnabled = false
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.incorrecto)
                mediaPlayer?.start()

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