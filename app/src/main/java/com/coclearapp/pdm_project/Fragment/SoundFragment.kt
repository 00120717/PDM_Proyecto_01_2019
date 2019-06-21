package com.coclearapp.pdm_project.Fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.coclearapp.pdm_project.Adapters.LettersAdapter
import com.coclearapp.pdm_project.Adapters.PatientAdapter
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Sound
import com.coclearapp.pdm_project.ViewModel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_grid_exercises.view.*

class SoundFragment(level: Int): Fragment(){
    private lateinit var viewAdapter: PatientAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(LevelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }


    private fun soundItemClicked(item: Sound){

        var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, item.sound)
        mediaPlayer?.start()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_grid_exercises, container, false)


        view.close_button.setOnClickListener {
            var fragment = LevelsSoundsFragment.newInstance()

            fragmentManager!!
                    .beginTransaction()
                    .setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out)
                    .replace(R.id.fl_content, fragment)
                    .commit()


        }

        view.rview.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = LettersAdapter(listOf(Sound(name = "ma",sound = R.raw.ma,number = 1, level = 1), Sound(name = "me",sound = R.raw.me,number = 2, level = 1), Sound(name = "mi",sound = R.raw.mi,number = 3, level = 1), Sound(name = "mo",sound = R.raw.mo,number = 4, level = 1), Sound(name = "mu",sound = R.raw.mu,number = 5, level = 1)),{ sounditem: Sound -> soundItemClicked(sounditem)})

        }



        return view


    }

    companion object {
        @JvmStatic
        fun newInstance(n: Int) = SoundFragment(n)

    }
    private lateinit var model: LevelViewModel
}