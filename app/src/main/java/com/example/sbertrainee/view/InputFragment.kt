package com.example.sbertrainee.view

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.sbertrainee.R
import com.example.sbertrainee.common.App
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.common.SimpleTextWatcher
import com.example.sbertrainee.presenter.InputPresenter
import kotlinx.android.synthetic.main.fragment_input.*
import kotlin.math.log

class InputFragment : Fragment(R.layout.fragment_input), Contract.InputView {

    private lateinit var inputPresenter: InputPresenter
    private val simpleTextWatcher = object : SimpleTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            inputPresenter.onTextChanged(s)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addListeners()
    }

    private fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        inputPresenter = InputPresenter(this, model)
//        viewPager.adapter = TraineeAdapter(traineePresenter.getTraineeList())
    }

    private fun addListeners() {
        editTextFullName.addTextChangedListener(simpleTextWatcher)
        editTextFullName.setOnFocusChangeListener { v, hasFocus ->
            inputPresenter.onEditTextFocusChange(v, hasFocus, activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?)
        }
        textInputLayout.setEndIconOnClickListener {
            inputPresenter.onEndIconClicked()
        }

        radioGroupGender.setOnCheckedChangeListener { _, checkedId ->
            inputPresenter.onGenderCheckedChange(checkedId)
        }
        checkBoxHasAlphaAccount.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onHasAlphaCheckedChange(isChecked)
        }
        checkBoxHasSigmaAccount.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onHasSigmaCheckedChange(isChecked)
        }
        checkBoxHasComputer.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onHasComputerCheckedChange(isChecked)
        }
        buttonAddTrainee.setOnClickListener {
            inputPresenter.onAddButtonClicked()
        }
    }

    override fun setTextToEditText(text: String) {
        editTextFullName.setText(text)
    }

    override fun setSelection(index: Int) {
        editTextFullName.setSelection(index)
    }

    override fun setEnabledButton(value: Boolean) {
        buttonAddTrainee.isEnabled = value
    }

    override fun clear() {
        editTextFullName.editableText?.clear()
        editTextFullName.clearFocus()
        radioGroupGender.clearCheck()
        checkBoxHasAlphaAccount.isChecked = false
        checkBoxHasSigmaAccount.isChecked = false
        checkBoxHasComputer.isChecked = false
    }

    companion object {
        @JvmStatic
        fun newInstance() = InputFragment()
    }
}