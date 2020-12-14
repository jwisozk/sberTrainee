package com.example.sbertrainee.model

import android.util.Log

class Model {

    var selectedItemPosition = 0
    private val traineeList: MutableList<Trainee> = ArrayList()

    fun addNewTrainee(trainee: Trainee) {
        traineeList.add(trainee)
    }

    fun getTraineeList(): List<Trainee> {
        return traineeList
    }

    fun removeCurrentTrainee() {
        if (traineeList.isNotEmpty())
            traineeList.removeAt(selectedItemPosition)
    }
}