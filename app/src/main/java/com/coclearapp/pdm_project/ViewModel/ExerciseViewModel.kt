package com.coclearapp.pdm_project.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coclearapp.pdm_project.Repository.ExerciseRepository
import com.coclearapp.pdm_project.Room.CoclearRoomDatabase
import com.coclearapp.pdm_project.Room.Entity.Exercise

class ExerciseViewModel(application: Application): AndroidViewModel(application){
    private val repository: ExerciseRepository

    init {
        val exerciseDao = CoclearRoomDatabase.getDatabase(application,viewModelScope).ExerciseDao()
        repository = ExerciseRepository(exerciseDao)
    }

    fun levelExercise(level: Int) = repository.getExerciseByLevel(level)

    fun exerciseById(id: Long) = repository.getExerciseById(id)

    fun getOneByLevelAndNumber(level: Int,number: Int) = repository.getOneByLevelAndNumber(level,number)
}