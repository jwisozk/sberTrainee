package com.example.sbertrainee.common

import androidx.annotation.StringRes
import com.example.sbertrainee.R

enum class GenderType(@StringRes val value: Int) {
    MAN(R.string.man),
    WOMAN(R.string.woman)
}