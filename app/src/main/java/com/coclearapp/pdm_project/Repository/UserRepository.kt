package com.coclearapp.pdm_project.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.coclearapp.pdm_project.Room.Dao.UserDao
import com.coclearapp.pdm_project.Room.Entity.User


class UserRepository(private val userDao: UserDao) {

    @WorkerThread
    suspend  fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    fun getAll(): LiveData<User> = userDao.getAllUser()


}