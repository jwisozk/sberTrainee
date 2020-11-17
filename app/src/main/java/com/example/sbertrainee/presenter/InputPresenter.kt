package com.example.sbertrainee.presenter

import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.model.Model

class InputPresenter(
    private val view: Contract.InputView,
    private val model: Model
) : Contract.InputPresenter {

    private val blockCharacters = "[\\d,.@#\$_&+()/*\"\':;!?%=|`~{}<>^]"

    override fun onTextChanged(s: CharSequence?) {
        val result: String = s.toString().trimStart().replace(Regex(blockCharacters), "")
        when {
            result != s.toString() -> {
                view.setTextToEditText(result)
                view.setSelection(result.length)
            }
            else ->  model.setFullName(result)
        }
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