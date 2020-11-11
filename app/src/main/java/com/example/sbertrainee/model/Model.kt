package com.example.sbertrainee.model

import com.example.sbertrainee.R

class Model {

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

    fun getTraineeList(): MutableList<TraineeData> =
        traineeList

    fun getFullName(): CharSequence? =
        fullName

    fun getGender(): String =
        gender ?: ""

    fun getHasAlphaAccount(): Boolean =
        hasAlphaAccount

    fun getHasSigmaAccount(): Boolean =
        hasSigmaAccount

    fun getHasComputer(): Boolean =
        hasComputer

    fun getGenderById(id: Int): String? =
        when (GenderType.values().singleOrNull { it.value == id }) {
            GenderType.MAN -> MAN
            GenderType.WOMAN -> WOMAN
            else -> null
        }

    fun addTrainee(traineeData: TraineeData) {
        traineeList.add(traineeData)
    }

    fun setFullName(s: CharSequence?) {
        fullName = s
    }

    fun setGender(s: String?) {
        gender = s
    }

    fun setHasAlphaAccount(isChecked: Boolean) {
        hasAlphaAccount = isChecked
    }

    fun setHasSigmaAccount(isChecked: Boolean) {
        hasSigmaAccount = isChecked
    }

    fun setHasComputer(isChecked: Boolean) {
        hasComputer = isChecked
    }

    fun checkValid(): Int? = when {
        fullName.isNullOrEmpty() -> ErrorType.FULL_NAME_ABSENT.value
        gender == null -> ErrorType.GENDER_ABSENT.value
        else -> null
    }

    fun clear() {
        fullName = null
        gender = null
        hasAlphaAccount = false
        hasSigmaAccount = false
        hasComputer = false
    }

    companion object {
        private const val MAN = "м"
        private const val WOMAN = "ж"
    }
}