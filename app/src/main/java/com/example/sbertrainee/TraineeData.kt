package com.example.sbertrainee

data class TraineeData(
    val fullName: String?,
    val gender: String?,
    val hasAlphaAccount: Boolean = false,
    val hasSigmaAccount: Boolean = false,
    val hasComputer: Boolean = false
)