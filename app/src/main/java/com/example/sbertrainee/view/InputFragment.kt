package com.example.sbertrainee.view

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
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
            inputPresenter.onInputNameChanged(s)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInputBinding.bind(view)
        fragmentInputBinding = binding
        init()
        initListeners()
    }

    private fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        inputPresenter = InputPresenter(this, model, resources)
    }

    private fun initListeners() {
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

        binding.radioMale.setOnClickListener {
            inputPresenter.onInputGenderMaleChecked()
        }

        binding.radioFemale.setOnClickListener {
            inputPresenter.onInputGenderFemaleChecked()
        }

        binding.checkBoxHasAlphaAccount.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onInputAlphaAccountChecked(isChecked)
        }
        binding.checkBoxHasSigmaAccount.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onInputSigmaAccountChecked(isChecked)
        }
        binding.checkBoxHasComputer.setOnCheckedChangeListener { _, isChecked ->
            inputPresenter.onInputComputerChecked(isChecked)
        }
        binding.buttonAddTrainee.setOnClickListener {
            inputPresenter.onAddButtonClicked()
        }
    }

    override fun notifyTraineeCatalogFragment() {
        setFragmentResult(Constants.REQUEST_INPUT_TRAINEE, Bundle())
    }

    override fun setInputName(name: String) {
        binding.editTextFullName.setText(name)
        binding.editTextFullName.setSelection(name.length)
    }

    override fun setEnabledAddButton(value: Boolean) {
        binding.buttonAddTrainee.isEnabled = value
        binding.buttonAddTrainee.alpha = when (value) {
            true -> Constants.ADD_TRAINEE_BUTTON_ALPHA_FULL
            else -> Constants.ADD_TRAINEE_BUTTON_ALPHA_MEDIUM
        }
    }

    override fun clearInputName() {
        binding.editTextFullName.editableText?.clear()
    }

    override fun clearInputGender() {
        binding.radioGroupGender.clearCheck()
    }

    override fun clearInputAlphaAccount() {
        binding.checkBoxHasAlphaAccount.isChecked = false
    }

    override fun clearInputSigmaAccount() {
        binding.checkBoxHasSigmaAccount.isChecked = false
    }

    override fun clearInputComputer() {
        binding.checkBoxHasComputer.isChecked = false
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentInputBinding = null
    }
}