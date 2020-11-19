package com.example.sbertrainee.model

data class TraineeData(
    var id: Int = 0,
    var fullName: String? = null,
    var gender: String? = null,
    var hasAlphaAccount: Boolean = false,
    var hasSigmaAccount: Boolean = false,
    var hasComputer: Boolean = false
)