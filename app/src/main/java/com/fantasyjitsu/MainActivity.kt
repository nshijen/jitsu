package com.fantasyjitsu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fantasyjitsu.mymatches.MyMatchesFragment
import com.fantasyjitsu.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onStart() {
        super.onStart()
        val myMatchesFragment = MyMatchesFragment.newInstance()
        val beginTransaction = supportFragmentManager.beginTransaction()

        val add = beginTransaction.add(R.id.fragment, myMatchesFragment, "Fragment")
        add.commit()
    }
}