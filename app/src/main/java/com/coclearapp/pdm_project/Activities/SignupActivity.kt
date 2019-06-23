package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Room.Entity.User
import com.coclearapp.pdm_project.ViewModel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*
import androidx.lifecycle.Observer


@Suppress("DEPRECATION")
class SignupActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var auth: FirebaseAuth
    private var mDatabase: DatabaseReference? = null
    private var tipoUser = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Instancias de Firebase
        mDatabase = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()



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
                    val childUpdates = HashMap<String, Any>()

                    //Guarda datos en Firebase
                    childUpdates.put("/User/" + user!!.uid + "/Name", input_name.text.toString())
                    childUpdates.put("/User/" + user!!.uid + "/Tipo", tipoUser)

                    //Guarda datos en BD
                    val viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
                    viewModel.insertUser(User(user.uid,input_name.text.toString(), tipoUser, ""))


                    mDatabase!!.updateChildren(childUpdates)
                    action()




                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Sign up failed.",
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

        if (password.isEmpty() || password.length < 6) {
            input_password!!.error = "Mayor que 6 caracteres alfanumericos"
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
            when (rg_tipo.checkedRadioButtonId) {
                rb_Voluntario.id -> tipoUser = 1
                rb_Terapeuta.id -> tipoUser = 2
                rb_Padres.id -> tipoUser = 3

            }

            rb_Voluntario!!.error = null
        }

        return valid
    }


    val progressDialog by lazy {
        ProgressDialog(this, R.style.AppTheme_Dark_Dialog)
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

}