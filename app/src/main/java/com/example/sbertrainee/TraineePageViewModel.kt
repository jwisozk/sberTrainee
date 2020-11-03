package com.example.sbertrainee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TraineePageViewModel : ViewModel() {
    private val _index = MutableLiveData<Int>()

    fun setIndex(index: Int) {
        _index.value = index
    }
}