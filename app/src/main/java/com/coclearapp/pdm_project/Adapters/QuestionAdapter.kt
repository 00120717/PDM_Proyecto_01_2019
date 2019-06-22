package com.coclearapp.pdm_project.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.Models.Patient
import com.coclearapp.pdm_project.Models.Question
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.cardview_patient.view.*
import kotlinx.android.synthetic.main.fragment_questions_container.view.*

class QuestionAdapter (var items : List<Question>, val clickListener : (Question) -> Unit): RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_questions_container, parent, false)
        return QuestionAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Question, clickListener: (Question) -> Unit) = with(itemView) {

            tv_question.text = item.number.toString()
            op_a.text = item.opt_a
            op_b.text = item.opt_b
            op_c.text = item.opt_c
            this.setOnClickListener { clickListener(item) }
        }
    }


}