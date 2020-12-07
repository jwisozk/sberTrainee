package com.example.sbertrainee.presenter

import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.presenter.adapter.TraineeAdapter

class TraineeCatalogPresenter(
    private val view: Contract.TraineeCatalogView,
    private val model: Model
) : Contract.TraineeCatalogPresenter {

    private val traineeAdapter: TraineeAdapter

    init {
        val traineeList = model.getTraineeList()
        traineeAdapter = TraineeAdapter(traineeList)
        view.setAdapter(traineeAdapter)
        view.setCurrentPage(model.getViewPagerCurrentItem())
    }

    override fun onAddButtonClicked() {
        showLastTrainee()
    }

    private fun showLastTrainee() {
        val traineeList = model.getTraineeList()
        traineeAdapter.submitList(traineeList)
        view.setCurrentPage(traineeList.size - 1)
    }

    override fun onPageSelected(position: Int) {
        model.setViewPagerCurrentItem(position)
    }
}