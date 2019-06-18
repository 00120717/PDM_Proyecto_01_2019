package com.example.pdm_project

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_levels_bar.*

class LevelsActivity: AppCompatActivity(),LevelBarFragment.OnTabListener{

    lateinit var viewModelLevel: LevelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        if (savedInstanceState == null) {
            var fragment: LevelsFragment = LevelsFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fl_content, fragment).commit()
        }

        btn_sounds.setOnClickListener {
            changeFragmentType()
        }

        btn_exercises.setOnClickListener {
            changeFragmentType()
        }

    }


    override fun onLevelSelect(typeLevel: String) {

        viewModelLevel.sounds = typeLevel

        changeFragmentType()
    }


    fun changeFragmentType() {
        var fragment = LevelsFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_content, fragment)
            .commit()
    }
}