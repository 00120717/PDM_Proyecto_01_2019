package com.coclearapp.pdm_project.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Patient")

data class Patient(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "Name_Patient")
        val Name_Patient: String,
        @ColumnInfo(name = "Date")
        val Date: String,
        @ColumnInfo(name = "Level")
        val Level: Int

)
