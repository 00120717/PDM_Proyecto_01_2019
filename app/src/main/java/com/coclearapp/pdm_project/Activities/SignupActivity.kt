package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        btn_signup.setOnClickListener { signup() }

        link_login.setOnClickListener {
            // Finish the registration screen and return to the Login activity
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }
    }

    fun signup() {
        Log.d(TAG, "Signup")

        if (!validate()) {
            onSignupFailed()
            return
        }

        btn_signup.isEnabled = false

        val progressDialog = ProgressDialog(
                this@SignupActivity,
                R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        // TODO: Implement your own signup logic here.

        android.os.Handler().postDelayed(
                {
                    // On complete call either onSignupSuccess or onSignupFailed
                    // depending on success
                    onSignupSuccess()
                    // onSignupFailed();
                    progressDialog.dismiss()
                }, 3000
        )
    }


    fun onSignupSuccess() {
        btn_signup.isEnabled = true
        setResult(RESULT_OK, null)
        finish()
    }

    fun onSignupFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()

        btn_signup.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true

        val name = input_name.text.toString()
        val password = input_password.text.toString()
        val reEnterPassword = input_reEnterPassword.text.toString()

        if (name.isEmpty() || name.length < 3) {
            input_name!!.error = "Al menos 3 caracteres"
            valid = false
        } else {
            input_name!!.error = null
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


        return valid
    }

    companion object {
        private val TAG = "SignupActivity"
    }
}