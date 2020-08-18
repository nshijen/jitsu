package com.fantasyjitsu.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fantasyjitsu.main.MainActivity
import com.fantasyjitsu.databinding.ActivityIntroBinding
import com.fantasyjitsu.login.LoginActivity
import com.fantasyjitsu.register.RegisterActivity

class IntroActivity : AppCompatActivity(){
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        binding.btnLetsPlay.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.btnLogin.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
        binding.btnRegister.setOnClickListener{ startActivity(Intent(this, RegisterActivity::class.java)) }
    }
}