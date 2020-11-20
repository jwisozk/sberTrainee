package com.example.sbertrainee.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.sbertrainee.R
import com.example.sbertrainee.App
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.presenter.ViewPagerPresenter
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager.*

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager), Contract.ViewPagerView {

    private lateinit var viewPagerPresenter: ViewPagerPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        viewPagerPresenter = ViewPagerPresenter(this, model, viewLifecycleOwner)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            viewPagerPresenter.onTabLayoutMediatorAttach(tab, position)
        }.attach()
    }

    override fun setAdapter(adapter: TraineeAdapter) {
        viewPager.adapter = adapter
    }

    override fun setCurrentPage(num: Int) {
        viewPager.currentItem = num
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ViewPagerFragment()
    }
}