package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.input_name
import kotlinx.android.synthetic.main.activity_login.input_password
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_login.setOnClickListener { login() }

        link_signup.setOnClickListener {
            // Start the Signup activity
            val intent = Intent(applicationContext, SignupActivity::class.java)
            startActivityForResult(intent, REQUEST_SIGNUP)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }
    }

    fun login() {
        Log.d(TAG, "Login")

        if (!validate()) {
            onLoginFailed()
            return
        }

        btn_login.isEnabled = false

        val progressDialog = ProgressDialog(
            this@LoginActivity,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Authenticating...")
        progressDialog.show()


        // TODO: Implement your own authentication logic here.

        android.os.Handler().postDelayed(
            {
                // On complete call either onLoginSuccess or onLoginFailed
                onLoginSuccess()
                // onLoginFailed();
                progressDialog.dismiss()
            }, 3000
        )
    }


    override  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish()
            }
        }
    }

    override fun onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true)
    }

    fun onLoginSuccess() {
        btn_login.isEnabled = true
        finish()
    }

    fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()

        btn_login.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true

        val name = input_name.text.toString()
        val password = input_password.text.toString()

        if (name.isEmpty() || name.length < 3) {
            input_name!!.error = "Al menos 3 caracteres"
            valid = false
        } else {
            input_name!!.error = null
        }



        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            input_password.error = "Entre 4 y 10 caracteres alfanumericos"
            valid = false
        } else {
            input_password.error = null
        }

        return valid
    }

    companion object {
        private val TAG = "LoginActivity"
        private val REQUEST_SIGNUP = 0
    }
}
