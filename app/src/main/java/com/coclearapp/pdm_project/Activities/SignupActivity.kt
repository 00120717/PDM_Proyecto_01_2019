package com.coclearapp.pdm_project.Activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.coclearapp.pdm_project.R
import com.coclearapp.pdm_project.Security.Encryption
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.input_name
import kotlinx.android.synthetic.main.activity_signup.input_password
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.text.DateFormat
import java.util.*
import javax.crypto.KeyGenerator


class SignupActivity : AppCompatActivity() {

    private var isSignedUp = false
    private var workingFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        workingFile = File(filesDir.absolutePath + File.separator +
                FileConstants.DATA_SOURCE_FILE_NAME)


        link_login.setOnClickListener {
            // Finish the registration screen and return to the Login activity
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun signupPressed(view: View) {
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
        //TODO:GUARDAR EN BASE
        saveLastLoggedInTime()

        // TODO:BORRAR
       // Log.d("decryptpassword",Encryption().decrypt())

        //Start main activity
        val context = view.context
        val Intent = Intent(context, MainActivity::class.java)
        Intent.putExtra(PWD_KEY2, input_password.toString().toCharArray())
        context.startActivity(Intent)

        Encryption().keystoreTest()





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


    private fun lastLoggedIn(): String? {
        //Get password
        val password = CharArray(input_password.length())
        input_password.text.getChars(0, input_password.length(), password, 0)

        //Retrieve shared prefs data
        val preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val base64Encrypted = preferences.getString("l", "")
        val base64Salt = preferences.getString("lsalt", "")
        val base64Iv = preferences.getString("liv", "")

        //Base64 decode
        val encrypted = Base64.decode(base64Encrypted, Base64.NO_WRAP)
        val iv = Base64.decode(base64Iv, Base64.NO_WRAP)
        val salt = Base64.decode(base64Salt, Base64.NO_WRAP)

        //Decrypt
        val decrypted = Encryption().decrypt(
            hashMapOf("iv" to iv, "salt" to salt, "encrypted" to encrypted), password)

        var lastLoggedIn: String? = null
        decrypted?.let {
            lastLoggedIn = String(it, Charsets.UTF_8)
        }
        return lastLoggedIn
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


    fun onSignupSuccess() {
        btn_signup.isEnabled = true
        setResult(RESULT_OK, null)
        finish()
    }

    fun onSignupFailed() {
        Toast.makeText(baseContext, "Sign up failed", Toast.LENGTH_LONG).show()

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

        //TODO:FALTA RADIOGROUP

        return valid
    }




    companion object {
        private val TAG = "SignupActivity"
        private const val PWD_KEY2 = "PWD"
    }

    object FileConstants {
        const val DATA_SOURCE_FILE_NAME = "coclear.dat"

    }
}