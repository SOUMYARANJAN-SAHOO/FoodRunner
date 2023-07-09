package com.kanha.foodrunner.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.kanha.foodrunner.R

class ForgotActivity : AppCompatActivity() {

    //Declaring sharedpreferences
    lateinit var sharedPreferences: SharedPreferences

    //initialising the views
    lateinit var nameEdt: TextInputEditText
    lateinit var phoneNumberEdt: TextInputEditText
    lateinit var getPassBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        //getting the shared prefes
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        //initialising the views
        nameEdt = findViewById(R.id.edtName)
        phoneNumberEdt = findViewById(R.id.edtMobileNumber)
        getPassBtn = findViewById(R.id.btnGetPassword)

        getPassBtn.setOnClickListener {
            if(sharedPreferences.getString("name",null) == nameEdt.text.toString()
                && sharedPreferences.getString("phone",null) == phoneNumberEdt.text.toString()){
                val password = sharedPreferences.getString("password","duck")
                Toast.makeText(this@ForgotActivity, "The password is $password", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                nameEdt.error = getString(R.string.wrong_username)
                phoneNumberEdt.error = getString(R.string.wrong_mobile_number)
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ForgotActivity, LoginActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}