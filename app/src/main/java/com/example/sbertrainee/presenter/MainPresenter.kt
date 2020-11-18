package com.example.sbertrainee.presenter

import android.content.Context
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.view.MainActivity

class MainPresenter(
    private val view: Contract.MainView
) : Contract.MainPresenter  {

    override fun onDispatchTouchEvent(event: MotionEvent, currentFocus: View?) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v: View? = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                }
            }
        }
    }

}