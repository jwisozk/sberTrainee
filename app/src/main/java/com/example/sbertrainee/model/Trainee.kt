package com.example.sbertrainee.model

import kotlinx.serialization.*

@Serializable
data class Trainee(
    val id: Int = 0,
    val fullName: String? = null,
    val gender: String? = null,
    val hasAlphaAccount: Boolean = false,
    val hasSigmaAccount: Boolean = false,
    val hasComputer: Boolean = false
)