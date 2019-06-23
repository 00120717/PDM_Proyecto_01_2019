package com.coclearapp.pdm_project.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.coclearapp.pdm_project.Room.Entity.User

@Dao
interface UserDao {

    @Query("SELECT * from User ORDER BY User.Name_User ASC")
    fun getAllUser(): LiveData<User>



    @Query("SELECT * from User WHERE User.Name_User==:name_user ")
    fun getByUser(name_user:String): LiveData<User>

    @Insert
    suspend fun insertUser(user: User)

    @Query("DELETE FROM User")
    fun deleteAllUser()
}