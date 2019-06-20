package com.coclearapp.pdm_project.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Rol")

data class Rol(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "Rol")
        val Rol: String

)
