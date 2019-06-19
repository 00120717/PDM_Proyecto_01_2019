package com.coclearapp.pdm_project.Activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import com.coclearapp.pdm_project.Fragment.LevelsExcersicesFragment
import com.coclearapp.pdm_project.Fragment.LevelsSoundsFragment
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.activity_levels.*


class LevelsActivity: AppCompatActivity(){

    lateinit var viewModelLevel: LevelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        patient_name_levels.text = intent.getStringExtra("name")

        btn_exercises.setOnClickListener {
            gif_help.visibility = View.INVISIBLE
            changeFragmentExercises()

        }

        btn_sounds.setOnClickListener {
            gif_help.visibility = View.INVISIBLE
            changeFragmentSounds()
        }

    }

    fun changeFragmentSounds() {
        var fragment = LevelsSoundsFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_content, fragment)
            .commit()
    }

    fun changeFragmentExercises() {
        var fragment = LevelsExcersicesFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_content, fragment)
            .commit()
    }
}