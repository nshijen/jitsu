package com.fantasyjutsu.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fantasyjutsu.BaseActivity
import com.fantasyjutsu.R
import com.fantasyjutsu.databinding.ActivityMainBinding
import com.fantasyjutsu.matches.MatchesFragment
import com.fantasyjutsu.mymatches.MyMatchesFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var matchesFragment:MatchesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val beginTransaction = supportFragmentManager.beginTransaction()
        val add = beginTransaction.add(binding.fragment.id, matchesFragment, "MatchesFragment")
        add.commit()
    }
}