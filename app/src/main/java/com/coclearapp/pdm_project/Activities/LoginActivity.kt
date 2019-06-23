@file:Suppress("DEPRECATION")

package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Repository.UserRepository
import com.coclearapp.pdm_project.Room.Entity.User
import com.coclearapp.pdm_project.ViewModel.UserViewModel

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.io.File
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*


class LoginActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        //Revisa si hay conexion a internet
        checkstatusInternet()

        //Valida si ya se ingreso anteriormente

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is signed in
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        } else {
            // User is signed out
            println("ha fallado***********************************************************+")
        }



        btn_login.setOnClickListener {

            signIn(lg_input_mail.text.toString(), lg_input_password.text.toString())

        }

        link_signup.setOnClickListener {
            // Start the Signup activity
            val intent = Intent(applicationContext, SignupActivity::class.java)
            startActivityForResult(intent, REQUEST_SIGNUP)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }
    }


    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")

        Log.d("validacion", validate().toString())
        if (!validate()) {
            return
        }

        showProgressDialog()

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser



                    action()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    hideProgressDialog()
                }


            }
        // [END sign_in_with_email]
    }


    private fun action() {
        startActivity(Intent(this, MainActivity::class.java))
    }


    override fun onBackPressed() {
        // Disable going back
        moveTaskToBack(true)
    }


    private fun validate(): Boolean {
        var valid = true


        val mail = lg_input_mail.text.toString()
        val password = lg_input_password.text.toString()

        if (mail.isEmpty()) {
            lg_input_mail!!.error = "Introduce correo"
            valid = false
        } else {
            lg_input_mail!!.error = null
        }
        if (password.isEmpty()) {
            lg_input_password!!.error = "Introduce contraseña"
            valid = false
        } else {
            lg_input_password!!.error = null
        }

        return valid
    }

    val progressDialog by lazy {
        ProgressDialog(this, R.style.AppTheme_Dark_Dialog)
    }

    private fun showProgressDialog() {
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Authenticating...")
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    private fun checkstatusInternet() {
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_LONG).show()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    companion object {
        private val TAG = "LoginActivity"
        private val REQUEST_SIGNUP = 0

    }


}
