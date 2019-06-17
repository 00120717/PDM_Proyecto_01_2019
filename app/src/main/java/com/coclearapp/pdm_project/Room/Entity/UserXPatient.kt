package com.coclearapp.pdm_project.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity(tableName = "UserXPatient",
        foreignKeys = [
                ForeignKey(entity = Patient::class,
                        parentColumns = ["Name_Patient"],
                        childColumns = ["Name_Patient"],
                        onDelete = CASCADE),
                ForeignKey(entity = User::class,
                        parentColumns = ["Name_User"],
                        childColumns = ["Name_User"],
                        onDelete = CASCADE)])

data class UserXPatient(

        @PrimaryKey(autoGenerate = true)
        val id:Int,
        @ColumnInfo(name = "Name_Patient")
        val Name_Patient: String,
        @ColumnInfo(name = "Name_User")
        val Name_User: String

)