package com.coclearapp.pdm_project.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coclearapp.pdm_project.Room.Entity.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * from Exercises WHERE Level==:level ")
    fun getExersicesByLevel(level: Int): LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE id_question = :id")
    fun getQuestionById(id: Long): LiveData<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(table : Exercise)


    @Query("SELECT * FROM Exercises WHERE Level = :level AND Number = :number")
    fun getQuestionByLevelAndNumber(level: Int, number: Int): LiveData<Exercise>

    @Query("DELETE FROM Exercises")
    fun deleteAllExercise()
}