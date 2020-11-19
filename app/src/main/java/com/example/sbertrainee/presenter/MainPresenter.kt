package com.example.sbertrainee.presenter

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.example.sbertrainee.common.Contract

class MainPresenter : Contract.MainPresenter  {

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