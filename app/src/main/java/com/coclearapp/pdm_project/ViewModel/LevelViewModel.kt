package com.coclearapp.pdm_project.ViewModel


import androidx.lifecycle.ViewModel
import com.coclearapp.pdm_project.Level

class LevelViewModel : ViewModel() { // TODO 2: ViewModel utilizado para compartir datos entre fragmentos
    var sounds = Level.SONIDOS
    var exercises = Level.EJERCICIOS
}