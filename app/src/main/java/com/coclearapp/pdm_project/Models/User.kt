package com.coclearapp.pdm_project.Models

data class User(
    var name:String,
    var email:String,
    var password:String,
    var patients:List<Patient>
)