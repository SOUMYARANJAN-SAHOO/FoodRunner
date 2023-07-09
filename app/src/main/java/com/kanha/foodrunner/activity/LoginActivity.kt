package com.kanha.foodrunner.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.kanha.foodrunner.R

class LoginActivity : AppCompatActivity() {

    //Declaring shared preferences
    lateinit var sharedPreferences: SharedPreferences

    //initialising the views
    lateinit var phoneNumberEdt: TextInputEditText
    lateinit var passwordEdt : TextInputEditText
    lateinit var loginBtn : Button
    lateinit var forgotPasswordBtn : Button
    lateinit var newRegistrationBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //getting the shared prefes
        sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

        //getting the login status
        var is_logged = sharedPreferences.getBoolean("isLoggedIn",false)
        //checking the login status
        if(is_logged){
            var to_home_screen = Intent(this@LoginActivity , FrameActivity::class.java)
            startActivity(to_home_screen)
            finish()
        }

        //initialising the views
        phoneNumberEdt = findViewById(R.id.edtMobileNumber)
        passwordEdt = findViewById(R.id.edtPassword)
        loginBtn = findViewById(R.id.btnLogin)
        forgotPasswordBtn = findViewById(R.id.btnForgot)
        newRegistrationBtn = findViewById(R.id.btnNewRegistration)

        //setting the other onclick listners
        forgotPasswordBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotActivity::class.java))
            finish()
        }
        newRegistrationBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, NewRegistrationActivity::class.java))
            finish()
        }



        loginBtn.setOnClickListener {
            val phoneNumber = phoneNumberEdt.text.toString()
            val password = passwordEdt.text.toString()

            if(sharedPreferences.getString("phone","1223334444") == phoneNumber && sharedPreferences.getString("password","duck") == password){
                val frameIntent = Intent(this@LoginActivity, FrameActivity::class.java)
                frameIntent.putExtra("phNumber", phoneNumber)
                startActivity(frameIntent)
                finish()
                //saving the loggin in sharedpreference
                sharedPreferences.edit().putBoolean("isLoggedIn",true)
                    .putString("phone","1223334444")
                    .putString("password","duck")
                    .apply()

            }else{
                phoneNumberEdt.error = getString(R.string.wrong_mobile_number)
                passwordEdt.error = getString(R.string.wrong_password)
            }
        }
    }
}