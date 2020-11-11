package com.example.sbertrainee.model

import androidx.lifecycle.ViewModel
import com.example.sbertrainee.R
import com.example.sbertrainee.common.Contract

class TraineeViewModel : ViewModel(), Contract.ViewModel {

    companion object {
        private const val MAN = "м"
        private const val WOMAN = "ж"
    }

    private enum class GenderType(val value: Int) {
        MAN(R.id.radioGenderMan),
        WOMAN(R.id.radioGenderWoman)
    }

    private enum class ErrorType(val value: Int) {
        FULL_NAME_ABSENT(R.string.full_name_absent),
        GENDER_ABSENT(R.string.gender_absent)
    }

    private val traineeList: MutableList<TraineeData> = ArrayList()
    private var fullName: CharSequence? = null
    private var gender: String? = null
    private var hasAlphaAccount: Boolean = false
    private var hasSigmaAccount: Boolean = false
    private var hasComputer: Boolean = false

    override fun getTraineeList(): MutableList<TraineeData> = traineeList
    override fun getFullName(): CharSequence? = fullName
    override fun getGender(): String? = gender
    override fun getHasAlphaAccount(): Boolean = hasAlphaAccount
    override fun getHasSigmaAccount(): Boolean = hasSigmaAccount
    override fun getHasComputer(): Boolean = hasComputer
    override fun getGenderById(id: Int): String? =
        when (GenderType.values().singleOrNull { it.value == id }) {
            GenderType.MAN -> MAN
            GenderType.WOMAN -> WOMAN
            else -> null
        }

    override fun addTrainee(traineeData: TraineeData) {
        traineeList.add(traineeData)
    }

    override fun setFullName(s: CharSequence?) {
        fullName = s
    }

    override fun setGender(s: String?) {
        gender = s
    }

    override fun setHasAlphaAccount(isChecked: Boolean) {
        hasAlphaAccount = isChecked
    }

    override fun setHasSigmaAccount(isChecked: Boolean) {
        hasSigmaAccount = isChecked
    }

    override fun setHasComputer(isChecked: Boolean) {
        hasComputer = isChecked
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
}