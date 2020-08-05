package com.fantasyjitsu.mymatches

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.fantasyjitsu.R

class MyMatchesFragment : Fragment() {

    companion object {
        fun newInstance() = MyMatchesFragment()
    }

    private lateinit var viewModel: MyMatchesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_matches_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyMatchesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pager = view.findViewById<ViewPager2>(R.id.my_matcher_pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tl_my_matches)
        pager.adapter = MatchesPagerAdapter(this)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }

}