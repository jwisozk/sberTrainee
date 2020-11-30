package com.example.sbertrainee.inrerface

import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import com.google.android.material.tabs.TabLayout

interface Contract {

    interface InputView {
        fun clear()
        fun setTextToEditText(text: String)
        fun setSelection(position: Int)
        fun setEnabledButton(value: Boolean)
    }

    interface ViewPagerView {
        fun setAdapter(adapter: TraineeAdapter)
        fun setCurrentPage(num: Int)
    }

    interface MainPresenter {
        fun onDispatchTouchEvent(event: MotionEvent, currentFocus: View?)
    }

    interface InputPresenter {
        fun onTextChanged(s: CharSequence?)
        fun onGenderCheckedChange(checkId: Int)
        fun onHasAlphaCheckedChange(isChecked: Boolean)
        fun onHasSigmaCheckedChange(isChecked: Boolean)
        fun onHasComputerCheckedChange(isChecked: Boolean)
        fun onAddButtonClicked()
        fun onEditTextFocusChange(
            view: View,
            hasFocus: Boolean,
            inputMethodManager: InputMethodManager?
        )

        fun onEndIconClicked()
    }

    interface ViewPagerPresenter {
        fun onTabLayoutMediatorAttach(tab: TabLayout.Tab, position: Int)
        fun onPageSelected(position: Int)
    }
}