package com.coclearapp.pdm_project.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.coclearapp.pdm_project.Room.Entity.Sound

@Dao
interface SoundDao {

    @Query("SELECT * from Sound ORDER BY Sound.number ASC")
    fun getAllSounds(): LiveData<List<Sound>>

}