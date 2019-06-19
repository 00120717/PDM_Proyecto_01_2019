package com.coclearapp.pdm_project.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.Adapters.PatientAdapter
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Patient
import kotlinx.android.synthetic.main.activity_patients.*

class PatientsActivity: AppCompatActivity(){
    private lateinit var viewAdapter: PatientAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patients)
        initRecycle(listOf(Patient(Name_Patient = "oscar",Date = "10",Level = 1),Patient(Name_Patient = "conan",Date = "15",Level = 3)))

    }

    fun initRecycle(patients : List<Patient>){
        viewManager = LinearLayoutManager(this)


        viewAdapter = PatientAdapter(patients,{ patientitem: Patient-> patientItemClicked(patientitem)})

        rv_patients.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun patientItemClicked(item: Patient){

        startActivity(Intent(this, LevelsActivity::class.java).putExtra("name",item.Name_Patient))
    }
}