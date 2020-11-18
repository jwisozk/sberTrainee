package com.example.sbertrainee.presenter

import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
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
        view.setEnabledButton(model.isDataEnough())
    }

    override fun onGenderCheckedChange(checkId: Int) {
        val gender = model.getGenderById(checkId)
        model.setGender(gender)
        view.setEnabledButton(model.isDataEnough())
    }

    override fun onEndIconClicked() {
        model.setFullName("")
        view.setTextToEditText("")
        view.setEnabledButton(model.isDataEnough())
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

    override fun onEditTextFocusChange(
        view: View,
        hasFocus: Boolean,
        inputMethodManager: InputMethodManager?
    ) {
        if (!hasFocus)
            hideKeyboard(view, inputMethodManager)
    }

    private fun hideKeyboard(view: View, inputMethodManager: InputMethodManager?) {
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //    override fun getTraineeList(): MutableList<TraineeData> =
//        model.getTraineeList()
//
//    override fun getCurrentItemViewPager(): Int =
//        model.getTraineeList().size - 1
}