package com.example.sbertrainee.model

import com.example.sbertrainee.common.ErrorType
import com.example.sbertrainee.common.GenderType

class Model {

    private val traineeList: MutableList<TraineeData> = ArrayList()

    fun getTraineeList(): MutableList<TraineeData> =
        traineeList

    fun addTrainee(traineeData: TraineeData) {
        traineeList.add(traineeData)
    }

    private var fullName: CharSequence? = null
    private var gender: String? = null
    private var hasAlphaAccount: Boolean = false
    private var hasSigmaAccount: Boolean = false
    private var hasComputer: Boolean = false

    private fun getFullName(): CharSequence? =
        fullName

    private fun getGender(): String =
        gender ?: ""

    private fun getHasAlphaAccount(): Boolean =
        hasAlphaAccount

    private fun getHasSigmaAccount(): Boolean =
        hasSigmaAccount

    private fun getHasComputer(): Boolean =
        hasComputer

    fun getGenderById(id: Int): String? =
        when (GenderType.values().singleOrNull { it.value == id }) {
            GenderType.MAN -> MAN
            GenderType.WOMAN -> WOMAN
            else -> null
        }

    fun getTraineeData(): TraineeData =
        TraineeData(
            getFullName().toString(),
            getGender(),
            getHasAlphaAccount(),
            getHasSigmaAccount(),
            getHasComputer()
        )

    fun setFullName(s: CharSequence?) {
        fullName = s?.trim()
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

    fun isDataEnough(): Boolean =
        when {
            fullName.isNullOrEmpty() -> false
            gender == null -> false
            else -> true
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