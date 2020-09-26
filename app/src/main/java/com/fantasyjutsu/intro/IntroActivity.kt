package com.fantasyjutsu.intro

import android.content.Intent
import android.os.Bundle
import com.fantasyjutsu.BaseActivity
import com.fantasyjutsu.databinding.ActivityIntroBinding
import com.fantasyjutsu.login.LoginActivity
import com.fantasyjutsu.main.MainActivity
import com.fantasyjutsu.register.RegisterActivity

class IntroActivity : BaseActivity(){
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