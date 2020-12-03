package com.example.sbertrainee.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.sbertrainee.R
import com.example.sbertrainee.App
import com.example.sbertrainee.databinding.FragmentViewPagerBinding
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.presenter.ViewPagerPresenter
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager), Contract.ViewPagerView {

    private lateinit var viewPagerPresenter: ViewPagerPresenter
    private var fragmentViewPagerBinding: FragmentViewPagerBinding? = null
    private lateinit var binding: FragmentViewPagerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewPagerBinding.bind(view)
        fragmentViewPagerBinding = binding
        init()
        listeners()
    }

    private fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        viewPagerPresenter = ViewPagerPresenter(this, model, viewLifecycleOwner)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewPagerPresenter.onPageSelected(position)
            }
        })
    }

    private fun listeners() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
        }.attach()
    }

    override fun setAdapter(adapter: TraineeAdapter) {
        binding.viewPager.adapter = adapter
    }

    override fun setCurrentPage(num: Int) {
        binding.viewPager.currentItem = num
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentViewPagerBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ViewPagerFragment()
    }
}