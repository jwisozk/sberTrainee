package com.example.sbertrainee.presenter

import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model

class TraineeCatalogPresenter(
    private val view: Contract.TraineeCatalogView,
    private val model: Model
) : Contract.TraineeCatalogPresenter {

    init {
        if (model.isTraineeCatalogFragmentAdded)
            view.setVisibleFragmentView()
        view.setAdapter(model.getTraineeList())
        view.setSelectedItemPosition(model.getSelectedItemPosition())
    }

    override fun onAddButtonClicked() {
        model.isTraineeCatalogFragmentAdded = true
        showLastTrainee()
    }

    private fun showLastTrainee() {
        val traineeList = model.getTraineeList()
        view.updateTraineeList(traineeList)
        view.setSelectedItemPosition(traineeList.size - 1)
    }

    override fun onItemPositionSelected(position: Int) {
        model.setSelectedItemPosition(position)
    }
}