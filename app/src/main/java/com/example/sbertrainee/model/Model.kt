package com.example.sbertrainee.model

class Model {

    private var viewPagerCurrentItem = 0
    private val traineeList: MutableList<Trainee> = ArrayList()

    fun addTrainee(trainee: Trainee) {
        traineeList.add(trainee)
    }

    fun getTraineeList(): List<Trainee> {
        return traineeList
    }

    fun getViewPagerCurrentItem(): Int {
        return viewPagerCurrentItem
    }

    fun setViewPagerCurrentItem(position: Int) {
        viewPagerCurrentItem = position
    }
}