package com.fantasyjutsu.mymatches

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MyMatchesViewModel @ViewModelInject constructor(@Assisted private val savedStateHandle: SavedStateHandle): ViewModel()