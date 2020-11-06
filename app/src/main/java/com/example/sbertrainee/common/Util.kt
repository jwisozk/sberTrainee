package com.example.sbertrainee.common

import android.view.View

object Util {
    fun changeVisible(view: View, isCheck: Boolean) = when(isCheck) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}