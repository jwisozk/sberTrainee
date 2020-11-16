package com.example.sbertrainee.presenter

import android.util.Log
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.model.Model

class InputPresenter(
    private val view: Contract.InputView,
    private val model: Model
) : Contract.InputPresenter {

    override fun onTextChanged(s: CharSequence?) {
        val result: String = s.toString().replace(Regex("[0-9]"), "")
        Log.d(this.toString(), "result: $result")
        if (result != s.toString()) {
            view.setTextToEditText(result)

//            Log.d(this.toString(), "result: $result")
        } else
            model.setFullName(result)
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
//            view.showErrorMessage(errorId)
        } else {
            val traineeData = model.getTraineeData()
            model.addTrainee(traineeData)
//            view.showTrainee(model.getTraineeList())
            view.clear()
            model.clear()
        }
    }

//    override fun getTraineeList(): MutableList<TraineeData> =
//        model.getTraineeList()
//
//    override fun getCurrentItemViewPager(): Int =
//        model.getTraineeList().size - 1
}