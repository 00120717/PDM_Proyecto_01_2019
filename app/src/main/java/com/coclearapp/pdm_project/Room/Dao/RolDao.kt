package com.coclearapp.pdm_project.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coclearapp.pdm_project.Room.Entity.Rol

@Dao
interface RolDao {

    @Query("SELECT * from Rol ORDER BY id ASC")
    fun getAllRol(): LiveData<List<Rol>>



    @Query("SELECT * from Rol WHERE Rol.id==:id ")
    fun getByid(id:Long): LiveData<Rol>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRol(rol: Rol)

    @Query("DELETE FROM Rol")
    fun deleteAllRol()
}