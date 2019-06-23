package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.coclearapp.pdm_project.Models.Patient
import com.coclearapp.pdm_project.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_pacient.*
import java.util.*
import kotlin.collections.HashMap

@Suppress("DEPRECATION")
class NewPatientActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var mDatabase: DatabaseReference? = null


    var calendar = Calendar.getInstance()
    var yearCurrent = calendar.get(Calendar.YEAR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pacient)

        mDatabase = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        Submit_Patient.setOnClickListener {
            savePatient()
        }
    }

    private fun savePatient() {

        if (!validate()) {
            return
        }

        showProgressDialog()
        //Firebase
        val user = auth.currentUser
        val childUpdates = HashMap<String, Any>()

        Log.d("userid", user!!.uid)


        //Guarda datos en Firebase
        // TODO: Consulta con la base
        var id = 1


        val patient = Patient(
            input_name_patient.text.toString(),
            "${id_patient_day.text}/${id_patient_month.text}/${id_patient_year.text}",
            0
        )
        childUpdates["/User/${user!!.uid}/Patient/$id"] = patient
        mDatabase!!.updateChildren(childUpdates)

        //Pasa a la actividad de pacientes
        action()

    }
    val progressDialog by lazy {
        ProgressDialog(this, R.style.AppTheme_Dark_Dialog)
    }

    private fun showProgressDialog() {
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Authenticating...")
        progressDialog.show()
    }

    private fun action() {
        startActivity(Intent(this, PatientsActivity::class.java))
    }
    private fun validate(): Boolean {
        var valid = true

        val name = input_name_patient.text.toString()
        val day = id_patient_day.text.toString()
        val month = id_patient_month.text.toString()
        val year = id_patient_year.text.toString()

        if (name.isEmpty() || name.length < 3) {
            input_name_patient!!.error = "Al menos 3 caracteres"
            valid = false
        } else {
            input_name_patient!!.error = null
        }
        if (id_patient_day.text.isEmpty()) {
            id_patient_day!!.error = "Introduce algun dia"
            valid = false
        } else
            if (day.toInt() < 1 || day.toInt() > 31) {
                id_patient_day!!.error = "Introduce dia correctamente"
                valid = false
            } else {
                id_patient_day!!.error = null
            }
        if (id_patient_month.text.isEmpty()){
            id_patient_month!!.error = "Introduce algun mes"
            valid = false
        }else
            if (month.toInt() < 1 || month.toInt() > 12) {
            id_patient_month!!.error = "Introduce mes correctamente"
            valid = false
        } else {
            id_patient_month!!.error = null
        }

        if (id_patient_year.text.isEmpty() ){
            id_patient_year!!.error = "Introduce algun año"
            valid = false
        }else
            if(year.toInt() > yearCurrent) {
            id_patient_year!!.error = "Introduce año correctamente"
            valid = false
        } else {
            id_patient_year!!.error = null
        }



        return valid


    }


}