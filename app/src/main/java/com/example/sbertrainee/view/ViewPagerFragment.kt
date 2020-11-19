package com.example.sbertrainee.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sbertrainee.R
import com.example.sbertrainee.common.App
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.model.TraineeData
import com.example.sbertrainee.presenter.InputPresenter
import com.example.sbertrainee.presenter.ViewPagerPresenter
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import kotlinx.android.synthetic.main.activity_main.*
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