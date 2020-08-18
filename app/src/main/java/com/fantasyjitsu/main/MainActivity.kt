package com.fantasyjitsu.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fantasyjitsu.databinding.ActivityMainBinding
import com.fantasyjitsu.mymatches.MyMatchesFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var myMatchesFragment:MyMatchesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val beginTransaction = supportFragmentManager.beginTransaction()
        val add = beginTransaction.add(binding.fragment.id, myMatchesFragment, "Fragment")
        add.commit()
    }
}