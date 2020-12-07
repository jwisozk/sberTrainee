package com.example.sbertrainee.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.viewpager2.widget.ViewPager2
import com.example.sbertrainee.R
import com.example.sbertrainee.App
import com.example.sbertrainee.Constants
import com.example.sbertrainee.databinding.FragmentTraineeCatalogBinding
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.presenter.TraineeCatalogPresenter
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import com.google.android.material.tabs.TabLayoutMediator

class TraineeCatalogFragment : Fragment(R.layout.fragment_trainee_catalog), Contract.TraineeCatalogView {

    private lateinit var traineeCatalogPresenter: TraineeCatalogPresenter
    private var fragmentTraineeCatalogBinding: FragmentTraineeCatalogBinding? = null
    private lateinit var binding: FragmentTraineeCatalogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTraineeCatalogBinding.bind(view)
        fragmentTraineeCatalogBinding = binding
        init()
        listeners()
    }

    private fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        traineeCatalogPresenter = TraineeCatalogPresenter(this, model)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                traineeCatalogPresenter.onPageSelected(position)
            }
        })
    }

    private fun listeners() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
        }.attach()
        setFragmentResultListener(Constants.REQUEST_INPUT_TRAINEE) { _, _ ->
            if (binding.root.visibility == View.INVISIBLE) {
                binding.root.visibility = View.VISIBLE
            }
            traineeCatalogPresenter.onAddButtonClicked()
        }
    }

    override fun setAdapter(adapter: TraineeAdapter) {
        binding.viewPager.adapter = adapter
    }

    override fun setCurrentPage(num: Int) {
        binding.viewPager.setCurrentItem(num, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentTraineeCatalogBinding = null
    }
}