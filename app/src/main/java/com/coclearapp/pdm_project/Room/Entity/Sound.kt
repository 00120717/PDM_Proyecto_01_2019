package com.coclearapp.pdm_project.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sound")

data class Sound(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "sound")
    val sound: Int,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "level")
    val level: Int


)
