package com.coclearapp.pdm_project.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "UserXPatient")

data class UserXPatient(
        @ColumnInfo(name = "Name_Patient")
        val Name_Patient: String,
        @ColumnInfo(name = "Name_User")
        val Name_User: String

)