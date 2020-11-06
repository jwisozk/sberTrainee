package com.example.sbertrainee.model

data class TraineesCell(
    val fullName: String,
    val gender: String,
    val hasAlphaAccount: Boolean = false,
    val hasSigmaAccount: Boolean = false,
    val hasComputer: Boolean = false,
) {
    companion object {
        private var mutableId = 0
    }

    init {
        mutableId++
    }

    val id = mutableId
}