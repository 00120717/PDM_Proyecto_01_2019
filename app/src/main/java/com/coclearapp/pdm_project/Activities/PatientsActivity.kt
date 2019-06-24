package com.coclearapp.pdm_project.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.Adapters.PatientAdapter


import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Patient
import com.coclearapp.pdm_project.ViewModel.PatientViewModel


import kotlinx.android.synthetic.main.activity_patients.*

class PatientsActivity: AppCompatActivity(), LifecycleOwner {
    private lateinit var viewAdapter: PatientAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var patientViewModel: PatientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patients)

        initRecycle(emptyList())

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel::class.java)

        patientViewModel.getAllPatients().observe(this, Observer {
            viewAdapter.dataChange(it)
        })

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