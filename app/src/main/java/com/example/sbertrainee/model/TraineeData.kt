package com.example.sbertrainee.model

data class TraineeData(
    val fullName: String,
    val gender: String,
    val hasAlphaAccount: Boolean = false,
    val hasSigmaAccount: Boolean = false,
    val hasComputer: Boolean = false,
) {

    init {
        mutableId++
    }

    val id = mutableId

    companion object {
        private var mutableId = 0
    }
}