package com.example.sbertrainee.presenter

import android.util.Log
import android.view.View
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.Trainee

class TraineeCatalogPresenter(
    private val view: Contract.TraineeCatalogView,
    private val model: Model
) : Contract.TraineeCatalogPresenter {

    init {
        if (model.getTraineeList().isNotEmpty())
            view.setVisibilityFragmentView(View.VISIBLE)
        view.setTraineeList(model.getTraineeList())
        view.setSelectedItemPosition(model.selectedItemPosition)
    }

    override fun refreshTraineeList() {
        val traineeList = model.getTraineeList()
        if (traineeList.isNotEmpty()) {
            updateTraineeList(traineeList)
            showLastTrainee(traineeList)
        } else {
            view.setVisibilityFragmentView(View.INVISIBLE)
        }
    }

    override fun onRemoveButtonClicked() {
        model.removeCurrentTrainee()
        refreshTraineeList()
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