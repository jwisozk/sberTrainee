package com.example.sbertrainee.presenter

import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.Trainee

class TraineeCatalogPresenter(
    private val view: Contract.TraineeCatalogView,
    private val model: Model
) : Contract.TraineeCatalogPresenter {

    init {
        if (model.getTraineeList().isNotEmpty())
            view.setVisibleFragmentView()
        view.setTraineeList(model.getTraineeList())
        view.setSelectedItemPosition(model.selectedItemPosition)
    }

    override fun onNewTraineeAdded() {
        val traineeList = model.getTraineeList()
        updateTraineeList(traineeList)
        showLastTrainee(traineeList)
    }

    private fun updateTraineeList(traineeList: List<Trainee>) {
        view.updateTraineeList(traineeList)
    }

    private fun showLastTrainee(traineeList: List<Trainee>) {
        view.setSelectedItemPosition(traineeList.size - 1)
    }

    override fun onItemPositionSelected(position: Int) {
        model.selectedItemPosition = position
    }
}