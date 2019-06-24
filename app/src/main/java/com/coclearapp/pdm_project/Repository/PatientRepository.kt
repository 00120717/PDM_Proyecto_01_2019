package com.coclearapp.pdm_project.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.coclearapp.pdm_project.Room.Dao.PatientDao
import com.coclearapp.pdm_project.Room.Entity.Patient

class PatientRepository(private val patientDao: PatientDao){

    fun getAllPatients():LiveData<List<Patient>> = patientDao.getAllPatient()

    @WorkerThread
    suspend  fun insertPatient(patient: Patient) {
        patientDao.insertPatient(patient)
    }

}