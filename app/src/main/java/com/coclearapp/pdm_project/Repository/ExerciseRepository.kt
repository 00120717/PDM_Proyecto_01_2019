package com.coclearapp.pdm_project.Repository

import androidx.lifecycle.LiveData
import com.coclearapp.pdm_project.Room.Dao.ExerciseDao
import com.coclearapp.pdm_project.Room.Entity.Exercise

class ExerciseRepository(private val exerciseDao: ExerciseDao){

    fun getExerciseByLevel(level: Int): LiveData<List<Exercise>> = exerciseDao.getExersicesByLevel(level)

    fun getExerciseById(id: Long):LiveData<Exercise> = exerciseDao.getQuestionById(id)
}