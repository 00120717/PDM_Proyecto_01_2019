package com.coclearapp.pdm_project.Repository

import androidx.lifecycle.LiveData
import com.coclearapp.pdm_project.Room.Dao.SoundDao
import com.coclearapp.pdm_project.Room.Entity.Sound

class SoundRepository(private val soundDao: SoundDao){

    fun allSounds(): LiveData<List<Sound>> = soundDao.getAllSounds()

    fun getSoundsByLevel(level: Int):LiveData<List<Sound>> = soundDao.getSoundsByLevel(level)
}