package com.coclearapp.pdm_project.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.coclearapp.pdm_project.Repository.UserRepository
import com.coclearapp.pdm_project.Room.CoclearRoomDatabase
import com.coclearapp.pdm_project.Room.Entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = CoclearRoomDatabase.getDatabase(application,viewModelScope).UserDao()
        repository = UserRepository(userDao)

    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(user)
    }

    fun getAllUser(): LiveData<User> = repository.getAll()
}