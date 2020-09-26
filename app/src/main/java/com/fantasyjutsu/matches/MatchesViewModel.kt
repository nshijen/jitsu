package com.fantasyjutsu.matches

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fantasyjutsu.api.Api
import com.fantasyjutsu.api.Network
import javax.inject.Inject

class MatchesViewModel @ViewModelInject constructor(api: Api, @Assisted private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    val api = api
    fun getMatches() {
        val matches = api.getMatches("1")


        matches?.subscribe({
            it?.let {
                if (it.isSuccessful) {
                    Log.d("TAG", "getMatches: successfull")
                    Log.d("TAG", "getMatches: " + it.body().toString())
                } else {
                    Log.d("TAG", "getMatches: failed")
                }
            }


        }, { t: Throwable? -> Log.d("TAG", "getMatches: "+t?.message) })
    }
}