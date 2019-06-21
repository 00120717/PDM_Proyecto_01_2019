package com.coclearapp.pdm_project.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.coclearapp.pdm_project.Repository.SoundRepository
import com.coclearapp.pdm_project.Room.CoclearRoomDatabase

class SoundViewModel(application: Application): AndroidViewModel(application){
    private val repository: SoundRepository

    init {
        val soundDao = CoclearRoomDatabase.getDatabase(application,viewModelScope).SoundDao()
        repository = SoundRepository(soundDao)
    }

    fun allSounds() = repository.allSounds()

    fun levelSounds(level: Int) = repository.getSoundsByLevel(level)
}