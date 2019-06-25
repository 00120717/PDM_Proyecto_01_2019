package com.coclearapp.pdm_project.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.coclearapp.pdm_project.Repository.PatientRepository
import com.coclearapp.pdm_project.Room.CoclearRoomDatabase
import com.coclearapp.pdm_project.Room.CoclearRoomDatabase_Impl
import com.coclearapp.pdm_project.Room.Entity.Patient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(application: Application) : AndroidViewModel(application) {

    private val patientRepo: PatientRepository

    init {
        val patientDao = CoclearRoomDatabase.getDatabase(application,viewModelScope).PatientDao()

        patientRepo = PatientRepository(patientDao)
    }

    //funcion obtener pacientes
    fun getAllPatients() = patientRepo.getAllPatients()


    fun getSearch(id: String, search: String) = patientRepo.searchPatiens(id,search)

    //funcion insertar paciente
    fun insertPatient(patient: Patient) = viewModelScope.launch(Dispatchers.IO) {
        patientRepo.insertPatient(patient)
    }

    //funcion para conseguir ultimo id
    fun getLastid() = patientRepo.getLastid()
}