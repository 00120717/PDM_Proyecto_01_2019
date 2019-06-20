package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Security.Encryption
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import java.io.File
import java.text.DateFormat
import java.util.*


@Suppress("DEPRECATION")
class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference

    private var workingFile: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        workingFile = File(
            filesDir.absolutePath + File.separator +
                    FileConstants.DATA_SOURCE_FILE_NAME
        )



        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("User")


        btn_signup.setOnClickListener {
            createAccount(input_mail.text.toString(), input_password.text.toString())
        }

        link_login.setOnClickListener {
            // Finish the registration screen and return to the Login activity
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validate()) {
            return
        }

        showProgressDialog()

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    saveLastLoggedInTime()
                    action()


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                    hideProgressDialog()
                }


            }
        // [END create_user_with_email]
    }

    private fun action() {
        startActivity(Intent(this, MainActivity::class.java))
    }


    private fun saveLastLoggedInTime() {
        //Get password
        val password = CharArray(input_password.length())
        input_password.text.getChars(0, input_password.length(), password, 0)

        //Base64 the data
        val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())
        val map =
            Encryption().encrypt(currentDateTimeString.toByteArray(Charsets.UTF_8), password)
        val valueBase64String = Base64.encodeToString(map["encrypted"], Base64.NO_WRAP)
        val saltBase64String = Base64.encodeToString(map["salt"], Base64.NO_WRAP)
        val ivBase64String = Base64.encodeToString(map["iv"], Base64.NO_WRAP)

        //Save to shared prefs
        val editor = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit()
        editor.putString("l", valueBase64String)
        editor.putString("lsalt", saltBase64String)
        editor.putString("liv", ivBase64String)
        editor.apply()


    }


    private fun validate(): Boolean {
        var valid = true

        val name = input_name.text.toString()
        val mail = input_mail.text.toString()
        val password = input_password.text.toString()
        val reEnterPassword = input_reEnterPassword.text.toString()

        if (name.isEmpty() || name.length < 3) {
            input_name!!.error = "Al menos 3 caracteres"
            valid = false
        } else {
            input_name!!.error = null
        }
        if (mail.isEmpty()) {
            input_mail!!.error = "Introduce correo"
            valid = false
        } else {
            input_mail!!.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            input_password!!.error = "Entre 4 y 10 caracteres alfanumericos"
            valid = false
        } else {
            input_password!!.error = null
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length < 4 || reEnterPassword.length > 10 || reEnterPassword != password) {
            input_reEnterPassword!!.error = "Contraseña no coincide"
            valid = false
        } else {
            input_reEnterPassword!!.error = null
        }

        if (rg_tipo.checkedRadioButtonId == -1) {
            rb_Voluntario!!.error = "Seleccionar algun tipo"
            valid = false
        } else {
            rb_Voluntario!!.error = null
        }

        return valid
    }




    val progressDialog by lazy {
        ProgressDialog(this,R.style.AppTheme_Dark_Dialog)
    }
    private fun showProgressDialog() {
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    companion object {
        private val TAG = "SignupActivity"
    }

    object FileConstants {
        const val DATA_SOURCE_FILE_NAME = "coclear.dat"

    }
}