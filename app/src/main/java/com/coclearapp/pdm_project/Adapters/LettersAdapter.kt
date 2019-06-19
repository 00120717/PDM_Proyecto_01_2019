package com.coclearapp.pdm_project.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Patient
import com.coclearapp.pdm_project.Room.Entity.Sound
import kotlinx.android.synthetic.main.cardview_patient.view.*
import kotlinx.android.synthetic.main.grid_main_page.view.*

class LettersAdapter(var letters:List<Sound>,val clicklistener: (Sound)->Unit):RecyclerView.Adapter<LettersAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LettersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_main_page, parent, false)
        return LettersAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return letters.size
    }

    override fun onBindViewHolder(holder: LettersAdapter.ViewHolder, position: Int) {
        holder.bind(letters[position], clicklistener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Sound, clickListener: (Sound) -> Unit) = with(itemView) {
            tv_item.text = item.name
            this.setOnClickListener { clickListener(item) }
        }
    }

}
