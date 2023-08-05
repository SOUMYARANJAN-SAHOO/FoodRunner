package com.kanha.foodrunner.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kanha.foodrunner.databinding.ActivityNewRegistrationBinding
import com.kanha.foodrunner.model.User

class NewRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewRegistrationBinding

    private lateinit var mAuth: FirebaseAuth

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance().reference

        binding.btnRegister.setOnClickListener {

            var name = binding.edtName.text.toString().trim()
            var email = binding.edtEmail.text.toString().trim()
            var phone = binding.edtMobileNumber.text.toString().trim()
            var address = binding.edtAddress.text.toString().trim()
            var password = binding.edtPassword.text.toString().trim()
            var confirmPassword = binding.edtConfirmPassword.text.toString().trim()

            when{
                name.isEmpty() -> binding.edtName.error = "Can't be blank"
                email.isEmpty() -> binding.edtEmail.error = "Can't be blank"
                phone.isEmpty() -> binding.edtMobileNumber.error = "Can't be blank"
                password.isEmpty() -> binding.edtPassword.error = "Can't be blank"
                confirmPassword.isEmpty() -> binding.edtConfirmPassword.error = "Can't be blank"
                password != confirmPassword -> {
                    binding.edtPassword.error = "Needed to be same"
                    binding.edtConfirmPassword.error = "Needed to be same"
                }
            }

            createUser(name, email, phone, address, password)

        }
    }

    private fun createUser(name: String, email: String, phone: String, address: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //adding the user to the database
                    addToDatabase(name, email, phone, address, mAuth.currentUser?.uid!!)

                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this@NewRegistrationActivity,FrameActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@NewRegistrationActivity, "Some error occurred!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addToDatabase(name: String, email: String, phone: String, address: String, uId: String){
        val user = User(name, phone, email, address, uId)
        database.child("users").child(uId).setValue(user)
    }

    override fun onBackPressed() {
        startActivity(Intent(this@NewRegistrationActivity, LoginActivity::class.java))
    }
}