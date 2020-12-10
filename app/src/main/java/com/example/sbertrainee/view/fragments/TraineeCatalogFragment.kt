package com.example.sbertrainee.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.viewpager2.widget.ViewPager2
import com.example.sbertrainee.R
import com.example.sbertrainee.App
import com.example.sbertrainee.databinding.FragmentTraineeCatalogBinding
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Trainee
import com.example.sbertrainee.presenter.TraineeCatalogPresenter
import com.example.sbertrainee.adapter.holder.TraineeAdapter
import com.example.sbertrainee.view.activity.MainActivity
import com.example.sbertrainee.view.fragments.constants.Constants
import com.google.android.material.tabs.TabLayoutMediator

class TraineeCatalogFragment : Fragment(R.layout.fragment_trainee_catalog), Contract.TraineeCatalogView {

    private lateinit var traineeCatalogPresenter: TraineeCatalogPresenter
    private var binding: FragmentTraineeCatalogBinding? = null
    private var traineeAdapter: TraineeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTraineeCatalogBinding.bind(view)
        init()
    }

    private fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        traineeCatalogPresenter = TraineeCatalogPresenter(this, model)
        binding?.let {
            it.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    traineeCatalogPresenter.onItemPositionSelected(position)
                }
            })
            TabLayoutMediator(it.tabLayout, it.viewPager) { _, _ ->
            }.attach()
            setFragmentResultListener(Constants.REQUEST_INPUT_TRAINEE) { _, _ ->
                setVisibleFragmentView()
                traineeCatalogPresenter.onNewTraineeAdded()
            }
        }
    }

    override fun setVisibleFragmentView() {
        binding?.root?.visibility = View.VISIBLE
    }

    override fun setTraineeList(traineeList: List<Trainee>) {
        traineeAdapter = TraineeAdapter(traineeList)
        binding?.viewPager?.adapter = traineeAdapter
    }

    override fun updateTraineeList(traineeList: List<Trainee>) {
        traineeAdapter?.submitList(traineeList)
    }

    override fun setSelectedItemPosition(position: Int) {
        binding?.viewPager?.setCurrentItem(position, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}