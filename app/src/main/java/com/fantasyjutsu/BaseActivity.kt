package com.fantasyjutsu

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.fantasyjutsu.databinding.ActivityBaseBinding
import kotlinx.android.synthetic.main.activity_base.view.*

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun setContentView(layoutResID: Int) {
        binding = ActivityBaseBinding.inflate(layoutInflater)
        layoutInflater.inflate(layoutResID, binding.root.fl_container, true)
        super.setContentView(binding.root)
    }

    override fun setContentView(view: View?) {
        binding = ActivityBaseBinding.inflate(layoutInflater)
        (binding.flContainer as FrameLayout).addView(view)
        super.setContentView(binding.root)
    }

    fun setProgressVisibility(visible: Boolean) {
        if (visible) binding.flProgressLayout.visibility = View.VISIBLE else binding.pbProgressbar.visibility = View.GONE
    }
}