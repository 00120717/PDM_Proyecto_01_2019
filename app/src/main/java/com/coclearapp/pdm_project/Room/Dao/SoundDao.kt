package com.coclearapp.pdm_project.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.coclearapp.pdm_project.Room.Entity.Patient
import com.coclearapp.pdm_project.Room.Entity.Sound

@Dao
interface SoundDao{

    @Query("SELECT * from Sound ORDER BY name")
    fun getAllSounds(): LiveData<List<Sound>>



    @Query("SELECT * from Sound WHERE name==:name ")
    fun getSoundBy(name:String): LiveData<Sound>

    @Insert
    suspend fun insertSound(sound: Sound)

    @Query("DELETE FROM Sound")
    fun deleteAllSounds()
}