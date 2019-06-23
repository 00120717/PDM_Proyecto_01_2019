package com.coclearapp.pdm_project.Models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Patient(
    var name: String,
    var birthday: String,
    var level: Int
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "birthday" to birthday,
            "level" to level
        )
    }
}