package com.example.sbertrainee.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sbertrainee.R
import com.example.sbertrainee.common.Contract

class TraineeViewModel : ViewModel(), Contract.ViewModel {

    companion object {
        private const val MAN = "м"
        private const val WOMAN = "ж"
    }

    enum class GenderType(val value: Int) {
        MAN(R.id.radioGenderMan),
        WOMAN(R.id.radioGenderWoman)
    }

    private val traineeList: MutableList<TraineeData> = ArrayList()
    private var fullName: CharSequence? = null
    private var gender: String? = null
    private var hasAlphaAccount: Boolean = false
    private var hasSigmaAccount: Boolean = false
    private var hasComputer: Boolean = false

    override fun getTraineeList(): MutableList<TraineeData> = traineeList

    override fun addTrainee(traineeData: TraineeData) {
        traineeList.add(traineeData)
    }

    override fun getFullName(): CharSequence? = fullName


    override fun setFullName(s: CharSequence?) {
        fullName = s
    }

    override fun getGender(): String? = gender

    override fun setGender(s: String?) {
        gender = s
    }

    override fun getGenderById(id: Int): String? =
        when (GenderType.values().singleOrNull { it.value == id }) {
            GenderType.MAN -> MAN
            GenderType.WOMAN -> WOMAN
            else -> null
        }

    override fun setHasAlphaAccount(isChecked: Boolean) {
        hasAlphaAccount = isChecked
    }

    override fun getHasAlphaAccount() : Boolean = hasAlphaAccount

    override fun setHasSigmaAccount(isChecked: Boolean) {
        hasSigmaAccount = isChecked
    }

    override fun getHasSigmaAccount(): Boolean = hasSigmaAccount

    override fun setHasComputer(isChecked: Boolean) {
        hasComputer = isChecked
    }

    override fun getHasComputer(): Boolean = hasComputer

    override fun onCleared() {
        super.onCleared()
        Log.i("TraineeViewModel", "TraineeViewModel destroyed!")
    }

    private enum class ErrorType(val value: Int) {
        FULL_NAME_ABSENT(R.string.full_name_absent),
        GENDER_ABSENT(R.string.gender_absent)
    }

    override fun checkValid(): Int? = when {
        fullName.isNullOrEmpty() -> ErrorType.FULL_NAME_ABSENT.value
        gender == null -> ErrorType.GENDER_ABSENT.value
        else -> null
    }

    override fun clear() {
        fullName = null
        gender = null
        hasAlphaAccount = false
        hasSigmaAccount = false
        hasComputer = false
    }
//    private fun checkValid(view: Contract.View): Pair<String, String>? {
//        val fullName = view.getFullName()
//        if (fullName.isEmpty()) {
//            view.showErrorMessage(ErrorType.FULL_NAME_ABSENT.value)
//            return null
//        }
//        if (gender == null) {
//            view.showErrorMessage(ErrorType.GENDER_ABSENT.value)
//            return null
//        }
//        return Pair(fullName, gender)
//    }

}