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

    override fun onInputNameChanged(s: CharSequence?, start: Int) {
        s?.let { name ->
            val result: String = formatInput(name)
            processInput(result, name, start)
            view.setEnabledAddButton(isDataEnough())

        } ?: throw IllegalArgumentException("Argument 's' is null in onInputNameChanged method")
    }

    private fun formatInput(name: CharSequence) =
        name.toString()
            .trimStart()
            .replace(Regex(blockCharacters), "")

    private fun processInput(result: String, name: CharSequence, start: Int) {
        val isInputChangedAfterFormat = result != name.toString()
        when {
            isInputChangedAfterFormat ->
                updateChangedInput(result, start)
            else ->
                updateTempTrainee(result)
        }
    }

    private fun updateTempTrainee(result: String) {
        val fullName = result.trim()
        traineeTmp =
            traineeTmp?.copy(fullName = fullName) ?: Trainee(fullName = fullName)
    }

    private fun updateChangedInput(result: String, start: Int) {
        if (result.isEmpty())
            view.setInputName(result, start)
        else
            view.setInputName(result, start + 1)
    }

    override fun onInputGenderMaleChecked() {
        val gender = resources.getString(R.string.gender_male)
        updateGender(gender)
    }

    override fun onInputGenderFemaleChecked() {
        val gender = resources.getString(R.string.gender_female)
        updateGender(gender)
    }

    private fun updateGender(gender: String) {
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

    @VisibleForTesting
    fun clearInputName() {
        onInputNameChanged("", 0)
    }

    @VisibleForTesting
    fun generateTraineeId() {
        val traineeList = model.traineeList
        var id = traineeList.size + 1
        if (traineeList.isNotEmpty()) {
            val lastId = traineeList.last().id
            if (traineeList.size != lastId) {
                val ids = traineeList.map(Trainee::id)

                for (currentId in 1 until lastId) {
                    if (!ids.contains(currentId)) {
                        id = currentId
                        break
                    }
                }
            }
        }
        // set id to temporary trainee
        traineeTmp = traineeTmp?.copy(id = id) ?: Trainee(id = id)
    }

    override fun onAddButtonClicked() {
        generateTraineeId()
        traineeTmp?.let {
            model.addNewTrainee(it)
            model.sortTraineeList()
            view.notifyNewTraineeAdded()
            clearInput()
        }
    }

    @VisibleForTesting
    fun clearInput() {
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