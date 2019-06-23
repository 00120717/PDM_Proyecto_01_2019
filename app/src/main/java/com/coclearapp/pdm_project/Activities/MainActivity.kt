package com.coclearapp.pdm_project.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coclearapp.pdm_project.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
        }

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

    override fun onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true)
    }
}