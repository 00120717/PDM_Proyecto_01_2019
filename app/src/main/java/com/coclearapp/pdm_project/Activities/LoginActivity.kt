package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import com.coclearapp.pdm_project.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.io.File

class LoginActivity : AppCompatActivity() {


    private var workingFile: File? = null
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Si ya ingreso se envie automaticamente a la pantalla principal
        /* if (updateLoggedInState()) {

             val context = applicationContext
             val intenttoMain = Intent(context, MainActivity::class.java)
             intenttoMain.putExtra(PWD_KEY, input_password.toString().toCharArray())
             context.startActivity(intenttoMain)
         }
 */
        auth = FirebaseAuth.getInstance()

        btn_login.setOnClickListener {
            signIn(lg_input_mail.text.toString(),lg_input_password.text.toString())
        }

        link_signup.setOnClickListener {
            // Start the Signup activity
            val intent = Intent(applicationContext, SignupActivity::class.java)
            startActivityForResult(intent, REQUEST_SIGNUP)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }
    }

    @VisibleForTesting
    val progressDialog by lazy {
        ProgressDialog(this)
    }
    fun showProgressDialog() {
        //progressDialog.setMessage(getString(R.string.loading))
        progressDialog.isIndeterminate = true
        progressDialog.show()
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!true) {
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
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    hideProgressDialog()
                }


            }
        // [END sign_in_with_email]
    }

    fun loginUser() {
        val mail: String = lg_input_mail.text.toString()
        val password: String = lg_input_password.text.toString()


        val progressDialog = ProgressDialog(
            this@LoginActivity,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Authenticating...")
        progressDialog.show()

        if (!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(password)) {

            auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        action()
                    } else {
                        Toast.makeText(this, "Error en autenticacion", Toast.LENGTH_SHORT)
                    }

                }
        }

    }

    private fun action() {
        startActivity(Intent(this, MainActivity::class.java))
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
