package com.coclearapp.pdm_project.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnlevel.setOnClickListener{
                startActivity(
                    Intent(
                        this,
                        LevelsActivity::class.java
                    )
                )
        }

        btnNew.setOnClickListener{
            startActivity(
                Intent(
                    this,
                    NewPatientActivity::class.java
                )
            )
        }

    }
}