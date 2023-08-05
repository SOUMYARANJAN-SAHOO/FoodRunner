package com.kanha.foodrunner.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.kanha.foodrunner.R
import com.kanha.foodrunner.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        var user = mAuth.currentUser
        //checking the login status
        if(user != null){
            var to_home_screen = Intent(this@LoginActivity , FrameActivity::class.java)
            startActivity(to_home_screen)
            finish()
        }

        //setting the other onclick listners
        binding.btnForgot.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotActivity::class.java))
            finish()
        }
        binding.btnNewRegistration.setOnClickListener {
            startActivity(Intent(this@LoginActivity, NewRegistrationActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if(email.isEmpty()){
                binding.edtEmail.error = "Can't be empty"
            }
            if(password.isNotEmpty()){
                binding.edtPassword.error = "Can't be empty"
            }

            loginUser(email,password)
        }
    }

    private fun loginUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val toHome = Intent(this@LoginActivity, FrameActivity::class.java)
                    finish()
                    startActivity(toHome)
                } else {
                    // If sign in fails, display a message to the user.
                    binding.edtEmail.error = getString(R.string.wrong_email)
                    binding.edtPassword.error = getString(R.string.wrong_password)
                }
            }
    }
}