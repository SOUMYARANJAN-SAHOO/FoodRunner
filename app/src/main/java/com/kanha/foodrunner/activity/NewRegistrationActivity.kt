package com.kanha.foodrunner.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.kanha.foodrunner.R
import com.kanha.foodrunner.databinding.ActivityNewRegistrationBinding
import kotlin.math.log

class NewRegistrationActivity : AppCompatActivity() {

    //Declaring sharedpreferences
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivityNewRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getting the shared prefes
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)



        binding.btnRegister.setOnClickListener {
            if(binding.edtPassword.text.toString() == binding.edtConfirmPassword.text.toString()){

                //updating the shared preferences
                sharedPreferences.edit().putString("name",binding.edtName.text.toString())
                    .putString("email",binding.edtEmail.text.toString())
                    .putString("phone",binding.edtMobileNumber.text.toString())
                    .putString("address",binding.edtAddress.text.toString())
                    .putString("password",binding.edtPassword.text.toString())
                    .apply()

                startActivity(Intent(this@NewRegistrationActivity,FrameActivity::class.java))
                finish()

            }else{
                Toast.makeText(this@NewRegistrationActivity, "Password and confirm password must be same", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@NewRegistrationActivity, LoginActivity::class.java))
    }
}