package com.example.sbertrainee.inrerface

import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import com.google.android.material.tabs.TabLayout

interface Contract {

    interface MainView {
        fun setTraineeCatalogFragmentVisible()
    }

    interface InputView {
        fun clearEditTextFullName()
        fun clearRadioGroupGender()
        fun clearCheckBoxHasAlphaAccount()
        fun clearCheckBoxHasSigmaAccount()
        fun clearCheckBoxHasComputer()
        fun setInputName(name: String)
        fun setSelection(position: Int)
        fun setEnabledButton(value: Boolean)
    }

    interface TraineeCatalogView {
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
        fun onClearButtonClicked()
    }

    interface TraineeCatalogPresenter {
        fun onPageSelected(position: Int)
    }
}