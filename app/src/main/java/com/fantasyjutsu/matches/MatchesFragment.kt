package com.fantasyjutsu.matches

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fantasyjutsu.api.Api
import com.fantasyjutsu.databinding.MatchesFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.fantasyjutsu.databinding.MyMatchesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MatchesFragment @Inject constructor():Fragment() {

    lateinit var viewModel:MatchesViewModel
    lateinit var binding: MatchesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MatchesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MatchesViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMatches()
    }


}