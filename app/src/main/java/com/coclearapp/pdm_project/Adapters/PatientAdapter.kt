package com.coclearapp.pdm_project.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coclearapp.pdm_project.Models.Patient
import com.coclearapp.pdm_project.R

import kotlinx.android.synthetic.main.cardview_patient.view.*

class PatientAdapter(var items : List<Patient>, val clickListener : (Patient) -> Unit) : RecyclerView.Adapter<PatientAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_patient, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }


    fun dataChange(lista_patients: List<Patient>) {
        items = lista_patients
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Patient, clickListener: (Patient) -> Unit) = with(itemView) {
            patient_name.text = item.name
            patient_level.text = item.level.toString()
            this.setOnClickListener { clickListener(item) }
        }
    }

}