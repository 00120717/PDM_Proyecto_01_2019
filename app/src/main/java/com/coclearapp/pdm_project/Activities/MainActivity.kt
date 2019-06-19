package com.coclearapp.pdm_project.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        add_patient.setOnClickListener{
                startActivity(
                    Intent(
                        this,
                        NewPatientActivity::class.java
                    )
                )
        }

        list_patients.setOnClickListener{
            startActivity(
                Intent(
                    this,
                    PatientsActivity::class.java
                )
            )
        }

    }
}