package com.example.pdm_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_levels.*


class LevelsActivity: AppCompatActivity(){

    lateinit var viewModelLevel: LevelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)


        btn_exercises.setOnClickListener {
            changeFragmentExercises()
        }

        btn_sounds.setOnClickListener {

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