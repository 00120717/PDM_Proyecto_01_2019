package com.example.pdm_project


import androidx.lifecycle.ViewModel

class LevelViewModel : ViewModel() { // TODO 2: ViewModel utilizado para compartir datos entre fragmentos
    var sounds = Level.SONIDOS
    var exercises =  Level.EJERCICIOS
}