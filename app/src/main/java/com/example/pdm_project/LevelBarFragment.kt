package com.example.pdm_project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_levels_bar.*

class LevelBarFragment: Fragment(){
    private var listener: OnTabListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_levels_bar, container, false).apply {
            btn_sounds.setOnClickListener {
                onButtonPressed(Level.SONIDOS)
            }
            btn_exercises.setOnClickListener {
                onButtonPressed(Level.EJERCICIOS)
            }

        }
    }

    fun onButtonPressed(season: String) {
        listener?.onLevelSelect(season)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTabListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnTabListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnTabListener {
        fun onLevelSelect(season: String)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LevelBarFragment().apply {
            }
    }


}

