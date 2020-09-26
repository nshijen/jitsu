package com.fantasyjutsu.matches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.fantasyjutsu.R

class MatchesAdapter: Adapter<MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_matches, parent, false)
        return MatchesViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {

    }


}