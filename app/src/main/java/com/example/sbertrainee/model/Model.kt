package com.example.sbertrainee.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sbertrainee.util.SingleEventLiveData

class Model {

    private val _isAddedViewPagerFragmentLiveData = SingleEventLiveData<Boolean>()
    val isAddedViewPagerFragmentLiveData: LiveData<Boolean> = _isAddedViewPagerFragmentLiveData

    private val _traineeListLiveData = SingleEventLiveData<MutableList<Trainee>>()
    val traineeListLiveData: LiveData<MutableList<Trainee>> = _traineeListLiveData

    private val _viewPagerCurrentItemLiveData = MutableLiveData<Int>()
    val viewPagerCurrentItemLiveData: LiveData<Int> = _viewPagerCurrentItemLiveData

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

    fun setIsAddedViewPagerFragment(value: Boolean) {
        _isAddedViewPagerFragmentLiveData.value = value
    }

    fun setViewPagerCurrentItemLiveData(position: Int) {
        _viewPagerCurrentItemLiveData.value = position
    }
}