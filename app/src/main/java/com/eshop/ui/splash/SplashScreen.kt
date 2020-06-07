package com.eshop.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eshop.MainActivity
import com.eshop.R
import com.eshop.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_splash_screen)
        val auth = FirebaseAuth.getInstance()
        var intent = Intent(this, MainActivity::class.java)
        if (auth.currentUser == null) {
            intent = Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)

        finish()
    }
}
