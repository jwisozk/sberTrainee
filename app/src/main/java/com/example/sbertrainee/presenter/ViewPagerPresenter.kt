package com.example.sbertrainee.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.Trainee
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import com.google.android.material.tabs.TabLayout
import kotlin.collections.ArrayList

class ViewPagerPresenter(
    private val view: Contract.ViewPagerView,
    private val model: Model,
    viewLifecycleOwner: LifecycleOwner
) : Contract.ViewPagerPresenter {

    private val traineeAdapter: TraineeAdapter

    init {
        val traineeList = model.traineeListLiveData.value ?: ArrayList()
        traineeAdapter = TraineeAdapter(traineeList)
        view.setAdapter(traineeAdapter)
        model.viewPagerCurrentItemLiveData.value?.let {
            view.setCurrentPage(it)
        }
        model.traineeListLiveData.observe(viewLifecycleOwner) { value ->
            showLastTrainee(value)
        }
    }

    override fun onTabLayoutMediatorAttach(tab: TabLayout.Tab, position: Int) {
        val trainee = model.getTraineeFromList(position)
        trainee?.let { t ->
            tab.text = "${(t.id)}.${t.fullName?.takeWhile { it.isLetter() }}"
        }
    }

    private fun showLastTrainee(traineeList: List<Trainee>) {
        traineeAdapter.submitList(traineeList)
        view.setCurrentPage(traineeList.size - 1)
    }

    override fun onPageSelected(position: Int) {
        model.setViewPagerCurrentItemLiveData(position)
    }
}