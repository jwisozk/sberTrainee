package com.example.sbertrainee.presenter

import android.content.res.Resources
import androidx.annotation.VisibleForTesting
import com.example.sbertrainee.R
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.Trainee

class InputPresenter(
    private val view: Contract.InputView,
    private val model: Model,
    private val resources: Resources
) : Contract.InputPresenter {

    private val blockCharacters = "[\\d,.@#\$_&+()/*\"\':;!?%=|`~{}<>^]"
    @VisibleForTesting
    var traineeTmp: Trainee? = null
    private var counterId = 0
 
    override fun onTextChanged(s: CharSequence?) {
        val result: String = s.toString().trimStart().replace(Regex(blockCharacters), "")
        when {
            result != s.toString() -> {
                view.setInputName(result)
                view.setSelection(result.length)
            }
            else -> {
                val fullName = result.trim()
                traineeTmp = traineeTmp?.copy(fullName = fullName) ?: Trainee(fullName = fullName)
            }
        }
        view.setEnabledButton(isDataEnough())
    }

    override fun onGenderCheckedChange(checkId: Int) {
        val genderRes = getGenderRes(checkId)
        genderRes?.let {
            val gender = resources.getString(it)
            traineeTmp = traineeTmp?.copy(gender = gender) ?: Trainee(gender = gender)
            view.setEnabledButton(isDataEnough())
        }
    }

    override fun onClearButtonClicked() {
        traineeTmp = traineeTmp?.copy(fullName = "")
        view.setInputName("")
        view.setEnabledButton(isDataEnough())
    }

    override fun onHasAlphaCheckedChange(isChecked: Boolean) {
        traineeTmp = traineeTmp?.copy(hasAlphaAccount = isChecked) ?: Trainee(hasAlphaAccount = isChecked)
    }

    override fun onHasSigmaCheckedChange(isChecked: Boolean) {
        traineeTmp = traineeTmp?.copy(hasSigmaAccount = isChecked) ?: Trainee(hasSigmaAccount = isChecked)
    }

    override fun onHasComputerCheckedChange(isChecked: Boolean) {
        traineeTmp = traineeTmp?.copy(hasComputer = isChecked) ?: Trainee(hasComputer = isChecked)
    }

    override fun onAddButtonClicked() {
        traineeTmp?.let {
            model.addTrainee(it.apply { id = ++counterId })
            view.clearEditTextFullName()
            view.clearRadioGroupGender()
            view.clearCheckBoxHasAlphaAccount()
            view.clearCheckBoxHasSigmaAccount()
            view.clearCheckBoxHasComputer()
            traineeTmp = null
            if (model.isAddedViewPagerFragmentLiveData.value == null) {
                model.setIsAddedViewPagerFragment(true)
            }
        }
    }

    private fun isDataEnough(): Boolean =
        when {
            traineeTmp?.fullName.isNullOrEmpty() -> false
            traineeTmp?.gender == null -> false
            else -> true
        }

    private fun getGenderRes(id: Int): Int? = when (id) {
        R.id.radioGenderMan -> R.string.gender_man
        R.id.radioGenderWoman -> R.string.gender_woman
        else -> null
    }

}