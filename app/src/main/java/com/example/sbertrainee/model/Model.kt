package com.example.sbertrainee.model

class Model {

    var selectedItemPosition = 0
    private val traineeList: MutableList<Trainee> = ArrayList()

    fun addNewTrainee(trainee: Trainee) {
        traineeList.add(trainee)
    }

    fun getTraineeList(): List<Trainee> {
        return traineeList
    }
}