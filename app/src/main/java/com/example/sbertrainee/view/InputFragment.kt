package com.example.sbertrainee.view

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.sbertrainee.R
import com.example.sbertrainee.App
import com.example.sbertrainee.Constants
import com.example.sbertrainee.databinding.FragmentInputBinding
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.util.SimpleTextWatcher
import com.example.sbertrainee.presenter.InputPresenter

class InputFragment : Fragment(R.layout.fragment_input), Contract.InputView {

    private var fragmentInputBinding: FragmentInputBinding? = null
    private lateinit var binding: FragmentInputBinding
    private lateinit var inputPresenter: InputPresenter
    private val simpleTextWatcher = object : SimpleTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            inputPresenter.onTextChanged(s)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInputBinding.bind(view)
        fragmentInputBinding = binding
        init()
        addListeners()
    }

    private fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        inputPresenter = InputPresenter(this, model, resources)
    }

    private fun addListeners() {
        binding.editTextFullName.addTextChangedListener(simpleTextWatcher)
        binding.editTextFullName.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val inputMethodManager = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
                inputMethodManager?.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
        binding.textInputLayout.setEndIconOnClickListener {
            inputPresenter.onClearButtonClicked()
        }

        binding.radioGroupGender.setOnCheckedChangeListener { _, checkedId ->
            inputPresenter.onGenderCheckedChange(checkedId)
        }
        binding.checkBoxHasAlphaAccount.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onHasAlphaCheckedChange(isChecked)
        }
        binding.checkBoxHasSigmaAccount.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onHasSigmaCheckedChange(isChecked)
        }
        binding.checkBoxHasComputer.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onHasComputerCheckedChange(isChecked)
        }
        binding.buttonAddTrainee.setOnClickListener {
            inputPresenter.onAddButtonClicked()
        }
    }

    override fun setInputName(name: String) {
        binding.editTextFullName.setText(name)
    }

    override fun setSelection(position: Int) {
        binding.editTextFullName.setSelection(position)
    }

    override fun setEnabledButton(value: Boolean) {
        binding.buttonAddTrainee.isEnabled = value
        binding.buttonAddTrainee.alpha = when (value) {
            true -> Constants.ADD_TRAINEE_BUTTON_ALPHA_FULL
            else -> Constants.ADD_TRAINEE_BUTTON_ALPHA_MEDIUM
        }
    }

    override fun clearEditTextFullName() {
        binding.editTextFullName.editableText?.clear()
    }

    override fun clearRadioGroupGender() {
        binding.radioGroupGender.clearCheck()
    }

    override fun clearCheckBoxHasAlphaAccount() {
        binding.checkBoxHasAlphaAccount.isChecked = false
    }

    override fun clearCheckBoxHasSigmaAccount() {
        binding.checkBoxHasSigmaAccount.isChecked = false
    }

    override fun clearCheckBoxHasComputer() {
        binding.checkBoxHasComputer.isChecked = false
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentInputBinding = null
    }
}