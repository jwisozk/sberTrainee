package com.example.sbertrainee.presenter

import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.TraineeData

class TraineePresenter(
    private val view: Contract.View,
    private val model: Model
) : Contract.Presenter {

    override fun onTextChanged(s: CharSequence?) {
        model.setFullName(s)
    }

    override fun onGenderCheckedChange(checkId: Int) {
        val gender = model.getGenderById(checkId)
        model.setGender(gender)
    }

    override fun onHasAlphaCheckedChange(isChecked: Boolean) {
        model.setHasAlphaAccount(isChecked)
    }

    override fun onHasSigmaCheckedChange(isChecked: Boolean) {
        model.setHasSigmaAccount((isChecked))
    }

    override fun onHasComputerCheckedChange(isChecked: Boolean) {
        model.setHasComputer(isChecked)
    }

    override fun onAddButtonClicked() {
        val errorId = model.checkValid()
        if (errorId != null) {
            view.showErrorMessage(errorId)
        } else {
            val traineeData = model.getTraineeData()
            model.addTrainee(traineeData)
            view.showTrainee(model.getTraineeList())
            view.clear()
            model.clear()
        }
    }

    override fun getTraineeList(): MutableList<TraineeData> =
        model.getTraineeList()

    override fun getCurrentItemViewPager(): Int =
        model.getTraineeList().size - 1
}