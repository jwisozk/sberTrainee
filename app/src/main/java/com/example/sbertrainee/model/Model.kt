package com.example.sbertrainee.model

import com.example.sbertrainee.view.fragments.util.ReadWriteFile

class Model {

    private var traineeIdShow = 1
    private var _selectedItemPosition = 0
    private var _traineeList: MutableList<Trainee> = ArrayList()
    val readWriteFile = ReadWriteFile()
    val selectedItemPosition
        get() = _selectedItemPosition

    val lastItemIndex: Int
        get() = traineeIdShow - 1

    val traineeList: List<Trainee>
        get() = _traineeList

    fun setSelectedItemPosition(position: Int) {
        if (position >= 0 && position < traineeList.size) {
            _selectedItemPosition = position
        }
    }

    fun setTraineeList(traineeList: List<Trainee>) {
        _traineeList = ArrayList(traineeList)
    }

    fun addNewTrainee(trainee: Trainee) {
        _traineeList.add(trainee)
        traineeIdShow = trainee.id
    }

    fun sortTraineeList() {
        _traineeList = _traineeList.sortedBy(Trainee::id).toMutableList()
    }

    fun removeTrainee(trainee: Trainee) {
        if (traineeList.isNotEmpty()) {
            _traineeList.remove(trainee)
            traineeIdShow = _selectedItemPosition + 1
        }
    }
}