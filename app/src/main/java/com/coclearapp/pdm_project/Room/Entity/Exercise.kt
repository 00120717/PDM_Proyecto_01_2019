package com.coclearapp.pdm_project.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Exercises")

data class Exercise(
    @ColumnInfo(name = "Number")
    val Number: Int,
    @ColumnInfo(name = "Name")
    val Sound: Int,
    @ColumnInfo(name = "Level")
    val Level: Int,
    @ColumnInfo(name = "Answer")
    val Answer: String,
    @ColumnInfo(name = "Optiona")
    val Optiona: String,
    @ColumnInfo(name = "Optionb")
    val Optionb: String,
    @ColumnInfo(name = "Optionc")
    val Optionc: String
){
    @PrimaryKey(autoGenerate = true)
    var id_question : Long = 0
}
