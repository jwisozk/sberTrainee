package com.example.sbertrainee.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sbertrainee.R
import com.example.sbertrainee.common.GenderType
import com.example.sbertrainee.common.SingleEventLiveData

class Model {

    private val _traineeListLiveData = SingleEventLiveData<MutableList<TraineeData>>()
    val traineeListLiveData: LiveData<MutableList<TraineeData>>
        get() = _traineeListLiveData

    init {
        _traineeListLiveData.value = ArrayList()
    }

    fun addTrainee(traineeData: TraineeData) {
        _traineeListLiveData.value?.add(traineeData)
        _traineeListLiveData.notifyObserver()
    }

    private var traineeData: TraineeData? = null

    private var counterId = 0

    fun getGender(id: Int): Int? = when (id) {
        R.id.radioGenderMan -> GenderType.MAN.value
        R.id.radioGenderWoman -> GenderType.WOMAN.value
        else -> null
    }

    fun getTraineeData(): TraineeData? =
        traineeData?.apply {
            this.id = ++counterId
        }

    fun setFullName(s: CharSequence?) {
        s?.let {
            val fullName = it.trim().toString()
            traineeData = traineeData?.copy(fullName = fullName) ?:
                    TraineeData(fullName = fullName)
        }
    }

    fun setGender(s: String) {
        traineeData = traineeData?.copy(gender = s) ?:
                TraineeData(gender = s)
    }

    fun setHasAlphaAccount(isChecked: Boolean) {
        traineeData = traineeData?.copy(hasAlphaAccount = isChecked) ?:
                TraineeData(hasAlphaAccount = isChecked)
    }

    fun setHasSigmaAccount(isChecked: Boolean) {
        traineeData = traineeData?.copy(hasSigmaAccount = isChecked) ?:
                TraineeData(hasSigmaAccount = isChecked)
    }

    fun setHasComputer(isChecked: Boolean) {
        traineeData =
            traineeData?.copy(hasComputer = isChecked) ?:
                    TraineeData(hasComputer = isChecked)
    }

    fun isDataEnough(): Boolean =
        when {
            traineeData?.fullName.isNullOrEmpty() -> false
            traineeData?.gender == null -> false
            else -> true
        }

    fun clear() {
        traineeData = TraineeData()
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}