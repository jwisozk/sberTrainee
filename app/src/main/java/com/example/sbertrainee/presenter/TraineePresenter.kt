package com.example.sbertrainee.presenter

import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.TraineeData

class TraineePresenter(
    private val view: Contract.View?,
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
        view?.let { v ->
            val errorId = model.checkValid()
            if (errorId != null) {
                v.showErrorMessage(errorId)
                return
            }
            val traineeData = TraineeData(
                model.getFullName().toString(),
                model.getGender(),
                model.getHasAlphaAccount(),
                model.getHasSigmaAccount(),
                model.getHasComputer()
            )
            model.addTrainee(traineeData)
            v.showTrainee(model.getTraineeList())
            v.clear()
            model.clear()
        }
    }

    override fun getCurrentItemViewPager(): Int
            = model.getTraineeList().size - 1
}