package com.example.sbertrainee.presenter

import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model

class InputPresenter(
    private val view: Contract.InputView,
    private val model: Model,
    private val resources: Resources
) : Contract.InputPresenter {

    private val blockCharacters = "[\\d,.@#\$_&+()/*\"\':;!?%=|`~{}<>^]"

    override fun onTextChanged(s: CharSequence?) {
        val result: String = s.toString().trimStart().replace(Regex(blockCharacters), "")
        when {
            result != s.toString() -> {
                view.setTextToEditText(result)
                view.setSelection(result.length)
            }
            else -> model.setFullName(result)
        }
        view.setEnabledButton(model.isDataEnough())
    }

    override fun onGenderCheckedChange(checkId: Int) {
        val genderRes = model.getGender(checkId)
        genderRes?.let {
            model.setGender(resources.getString(it))
            view.setEnabledButton(model.isDataEnough())
        }
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
        val trainee = model.newTrainee()
        trainee?.let {
            model.addTrainee(it)
            view.clear()
            model.clear()
            if (model.isAddedViewPagerFragmentLiveData.value == null)
                model.setIsAddedViewPagerFragment(true)
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
}