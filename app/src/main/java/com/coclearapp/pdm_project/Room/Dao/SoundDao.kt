package com.coclearapp.pdm_project.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coclearapp.pdm_project.Room.Entity.Sound

@Dao
interface SoundDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(table : Sound)


    @Query("SELECT * from Sounds ORDER BY Sounds.number ASC")
    fun getAllSounds(): LiveData<List<Sound>>


    @Query("SELECT * FROM sounds WHERE level = :level")
    fun getSoundsByLevel(level: Int): LiveData<List<Sound>>


}