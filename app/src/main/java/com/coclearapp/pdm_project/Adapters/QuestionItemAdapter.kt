package com.coclearapp.pdm_project.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.Exercise
import com.coclearapp.pdm_project.Room.Entity.Sound
import kotlinx.android.synthetic.main.level_item.view.*


class QuestionItemAdapter(var items : List<Exercise>, val clickListener : (Exercise) -> Unit): RecyclerView.Adapter<QuestionItemAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.level_item, parent, false)
        return QuestionItemAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: QuestionItemAdapter.ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    fun dataChange(lista_exercises : List<Exercise>){
        items = lista_exercises
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Exercise, clickListener: (Exercise) -> Unit) = with(itemView) {

            tv_item.text = item.Number.toString()
            this.setOnClickListener { clickListener(item) }
        }
    }


}