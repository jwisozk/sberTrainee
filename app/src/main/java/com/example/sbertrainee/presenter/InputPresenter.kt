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

    override fun onInputNameChanged(s: CharSequence?) {
        val result: String = s.toString().trimStart().replace(Regex(blockCharacters), "")
        when {
            result != s.toString() -> {
                view.setInputName(result)
            }
            else -> {
                val fullName = result.trim()
                traineeTmp = traineeTmp?.copy(fullName = fullName) ?: Trainee(fullName = fullName)
            }
        }
        view.setEnabledAddButton(isDataEnough())
    }

    override fun onClearInputNameButtonClicked() {
        clearInputName()
    }

    override fun onInputGenderMaleChecked() {
        val gender = resources.getString(R.string.gender_male)
        setGender(gender)
    }

    override fun onInputGenderFemaleChecked() {
        val gender = resources.getString(R.string.gender_female)
        setGender(gender)
    }

    private fun setTraineeId() {
        val traineeList = model.traineeList
        var id = traineeList.size + 1
        if (traineeList.isNotEmpty()) {
            val lastId = traineeList.last().id
            if (traineeList.size != lastId) {
                val list = ArrayList<Int>()
                traineeList.forEach { list.add(it.id) }
                for (i in 1 until lastId) {
                    if (!list.contains(i)) {
                        id = i
                        break
                    }
                }
            }
        }
        traineeTmp = traineeTmp?.copy(id = id)
    }


    private fun setGender(gender: String) {
        traineeTmp = traineeTmp?.copy(gender = gender) ?: Trainee(gender = gender)
        view.setEnabledAddButton(isDataEnough())
    }

    override fun onInputAlphaAccountChecked(isChecked: Boolean) {
        traineeTmp =
            traineeTmp?.copy(hasAlphaAccount = isChecked) ?: Trainee(hasAlphaAccount = isChecked)
    }

    override fun onInputSigmaAccountChecked(isChecked: Boolean) {
        traineeTmp =
            traineeTmp?.copy(hasSigmaAccount = isChecked) ?: Trainee(hasSigmaAccount = isChecked)
    }

    override fun onInputComputerChecked(isChecked: Boolean) {
        traineeTmp = traineeTmp?.copy(hasComputer = isChecked) ?: Trainee(hasComputer = isChecked)
    }

    private fun clearInputName() {
        onInputNameChanged("")
        view.setInputName("")
    }

    override fun onAddButtonClicked() {
        setTraineeId()
        traineeTmp?.let {
            model.addNewTrainee(it)
            model.sortTraineeList()
            view.notifyNewTraineeAdded()
            clearInput()
        }
    }

    private fun clearInput() {
        clearInputName()
        view.clearInputGender()
        view.clearInputAlphaAccount()
        view.clearInputSigmaAccount()
        view.clearInputComputer()
        traineeTmp = null
    }

    @VisibleForTesting
    fun isDataEnough(): Boolean =
        when {
            traineeTmp?.fullName.isNullOrEmpty() -> false
            traineeTmp?.gender == null -> false
            else -> true
        }
}