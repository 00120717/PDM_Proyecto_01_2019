package com.coclearapp.pdm_project.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.coclearapp.pdm_project.Room.Entity.UserXPatient


@Dao
interface UserXPatientDao {

    @Query("SELECT * from UserXPatient ORDER BY UserXPatient.Name_User ASC")
    fun getAllUserXPatientDao(): LiveData<List<UserXPatient>>


/*
    @Query("SELECT * from UserXPatient WHERE .id==:id ")
    fun getBy(id:Long): LiveData<UserDao>*/

    @Insert
    suspend fun insertUserXPatientDao(userXPatient: UserXPatient)

    @Query("DELETE FROM UserXPatient")
    fun deleteAllUserXPatientDao()
}