package com.fantasyjutsu.mymatches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.fantasyjutsu.databinding.MyMatchesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyMatchesFragment  @Inject constructor():Fragment() {
    lateinit var viewModel:MyMatchesViewModel
    lateinit var binding: MyMatchesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyMatchesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyMatchesViewModel::class.java)
        binding.myMatcherPager.adapter = MatchesPagerAdapter(this)
        TabLayoutMediator(binding.tlMyMatches, binding.myMatcherPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }
}