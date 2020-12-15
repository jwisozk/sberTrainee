package com.example.sbertrainee.model

class Model {

    var selectedItemPosition = 0
    var traineeIdShow = 1
    var traineeList: MutableList<Trainee> = ArrayList()

    fun addNewTrainee(trainee: Trainee) {
        traineeList.add(trainee)
        traineeIdShow = trainee.id
    }

    fun sortTraineeList() {
        traineeList = traineeList.sortedBy { it.id }.toMutableList()
    }

    fun removeCurrentTrainee() {
        if (traineeList.isNotEmpty()) {
            traineeList.removeAt(selectedItemPosition)
            traineeIdShow = selectedItemPosition + 1
        }
    }
}