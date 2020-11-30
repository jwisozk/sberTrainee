package com.example.sbertrainee.model

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sbertrainee.R
import com.example.sbertrainee.enums.GenderType
import com.example.sbertrainee.util.SingleEventLiveData

class Model {

    private val _isAddedViewPagerFragmentLiveData = SingleEventLiveData<Boolean>()
    val isAddedViewPagerFragmentLiveData: LiveData<Boolean> = _isAddedViewPagerFragmentLiveData

    private val _traineeListLiveData = SingleEventLiveData<MutableList<Trainee>>()
    val traineeListLiveData: LiveData<MutableList<Trainee>> = _traineeListLiveData

    private val _viewPagerCurrentItemLiveData = SingleEventLiveData<Int>()
    val viewPagerCurrentItemLiveData: LiveData<Int> = _viewPagerCurrentItemLiveData

    private var counterId = 0

    @VisibleForTesting
    var trainee: Trainee? = null

    init {
        _traineeListLiveData.value = ArrayList()
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    fun addTrainee(trainee: Trainee) {
        _traineeListLiveData.value?.add(trainee)
        _traineeListLiveData.notifyObserver()
    }

    fun getGenderRes(id: Int): Int? = when (id) {
        R.id.radioGenderMan -> GenderType.MAN.value
        R.id.radioGenderWoman -> GenderType.WOMAN.value
        else -> null
    }

    fun newTrainee(): Trainee? =
        trainee?.let { it ->
            val id = counterId + 1
            return when (traineeListLiveData.value?.find { it.id == id }) {
                null -> {
                    counterId = id
                    it.apply {
                        this.id = id
                    }
                }
                else -> null
            }
        }

    fun getTraineeFromList(position: Int): Trainee? {
        val trainee = traineeListLiveData.value
        trainee?.let {
            return when (position) {
                in 0 until it.size -> it[position]
                else -> null
            }
        }
        return null
    }

    fun setFullName(s: CharSequence?) {
        s?.let {
            val fullName = it.trim().toString()
            trainee = trainee?.copy(fullName = fullName) ?: Trainee(fullName = fullName)
        }
    }

    fun setGender(s: String) {
        trainee = trainee?.copy(gender = s) ?: Trainee(gender = s)
    }

    fun setHasAlphaAccount(isChecked: Boolean) {
        trainee = trainee?.copy(hasAlphaAccount = isChecked) ?: Trainee(hasAlphaAccount = isChecked)
    }

    fun setHasSigmaAccount(isChecked: Boolean) {
        trainee = trainee?.copy(hasSigmaAccount = isChecked) ?: Trainee(hasSigmaAccount = isChecked)
    }

    fun setHasComputer(isChecked: Boolean) {
        trainee =
            trainee?.copy(hasComputer = isChecked) ?: Trainee(hasComputer = isChecked)
    }

    fun setIsAddedViewPagerFragment(value: Boolean) {
        _isAddedViewPagerFragmentLiveData.value = value
    }

    fun setViewPagerCurrentItemLiveData(position: Int) {
        _viewPagerCurrentItemLiveData.value = position
    }

    fun isDataEnough(): Boolean =
        when {
            trainee?.fullName.isNullOrEmpty() -> false
            trainee?.gender == null -> false
            else -> true
        }

    fun clear() {
        trainee = null
    }
}