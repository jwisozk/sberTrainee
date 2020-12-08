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
        if (model.isTraineeCatalogFragmentAdded)
            view.setVisibleFragmentView()
        val traineeList = model.getTraineeList()
        traineeAdapter = TraineeAdapter(traineeList)
        view.setAdapter(traineeAdapter)
        view.setSelectedItemPosition(model.getSelectedItemPosition())
    }

    override fun onAddButtonClicked() {
        model.isTraineeCatalogFragmentAdded = true
        showLastTrainee()
    }

    private fun showLastTrainee() {
        val traineeList = model.getTraineeList()
        traineeAdapter.submitList(traineeList)
        view.setSelectedItemPosition(traineeList.size - 1)
    }

    override fun onItemPositionSelected(position: Int) {
        model.setSelectedItemPosition(position)
    }
}