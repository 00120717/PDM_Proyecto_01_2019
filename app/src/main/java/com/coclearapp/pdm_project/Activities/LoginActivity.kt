package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.coclearapp.pdm_project.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.input_name
import kotlinx.android.synthetic.main.activity_login.input_password
import kotlinx.android.synthetic.main.activity_signup.*
import java.io.File

class LoginActivity : AppCompatActivity() {


    private var isSignedUp = false
    private var workingFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Si ya ingreso se envie automaticamente a la pantalla principal
        if (updateLoggedInState()) {

            val context = applicationContext
            val intenttoMain = Intent(context, MainActivity::class.java)
            intenttoMain.putExtra(PWD_KEY, input_password.toString().toCharArray())
            context.startActivity(intenttoMain)
        }



        link_signup.setOnClickListener {
            // Start the Signup activity
            val intent = Intent(applicationContext, SignupActivity::class.java)
            startActivityForResult(intent, REQUEST_SIGNUP)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }
    }


    private fun updateLoggedInState(): Boolean {
        val fileExists = workingFile?.exists() ?: false
        return fileExists
    }


    fun loginPressed(view: View) {
        Log.d(TAG, "Login")

        btn_login.isEnabled = false

        val progressDialog = ProgressDialog(
            this@LoginActivity,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Authenticating...")
        progressDialog.show()


        //  Implement your own authentication logic here.
        // TODO: Validar en la base name y password

        if (true) {
            //Start main activity
            val context = view.context
            val Intent = Intent(context, MainActivity::class.java)
            Intent.putExtra(PWD_KEY, input_password.toString().toCharArray())
            context.startActivity(Intent)



        } else {
            onLoginFailed()
        }


        android.os.Handler().postDelayed(
            {
                // On complete call either onLoginSuccess or onLoginFailed
                onLoginSuccess()
                // onLoginFailed();
                progressDialog.dismiss()
            }, 3000
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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

    companion object {
        private val TAG = "LoginActivity"
        private val REQUEST_SIGNUP = 0
        private const val PWD_KEY = "PWD"
    }


}
