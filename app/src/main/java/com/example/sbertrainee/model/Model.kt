package com.example.sbertrainee.model

class Model {

    private var selectedItemPosition = 0
    private val traineeList: MutableList<Trainee> = ArrayList()
    var isTraineeCatalogFragmentAdded = false

    fun addNewTrainee(trainee: Trainee) {
        traineeList.add(trainee)
    }

    fun getTraineeList(): List<Trainee> {
        return traineeList
    }

    fun getSelectedItemPosition(): Int {
        return selectedItemPosition
    }

    fun setSelectedItemPosition(position: Int) {
        selectedItemPosition = position
    }
}