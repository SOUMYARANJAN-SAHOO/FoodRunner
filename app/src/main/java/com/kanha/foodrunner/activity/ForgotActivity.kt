package com.kanha.foodrunner.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kanha.foodrunner.databinding.ActivityForgotBinding

class ForgotActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityForgotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
        binding.btnGetPassword.setOnClickListener {
            Toast.makeText(this@ForgotActivity, "This feature should be added soon.", Toast.LENGTH_SHORT).show()
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