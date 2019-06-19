package com.coclearapp.pdm_project.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")

data class User(
        @PrimaryKey
        @ColumnInfo(name = "Name_User")
        val Name_User: String,
        @ColumnInfo(name = "Password")
        val Password: String,
        @ColumnInfo(name = "Rol")
        val Rol: Int,
        @ColumnInfo(name = "Patients")
        val Patients: String
)
