package com.kanha.foodrunner.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.RelativeLayout
import com.kanha.foodrunner.R

class SplashActivity : AppCompatActivity() {
    
    //initialising the layout
    lateinit var splashLayout: RelativeLayout
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        splashLayout = findViewById(R.id.splashScreen)
        
        Handler().postDelayed({
            val loginIntent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }, 2000)
        
    }
}