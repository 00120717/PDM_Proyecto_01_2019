package com.coclearapp.pdm_project.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.coclearapp.pdm_project.Room.Entity.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * from Exercises WHERE Level==:level ")
    fun getExersicesByLevel(level: Int): LiveData<List<Exercise>>

}