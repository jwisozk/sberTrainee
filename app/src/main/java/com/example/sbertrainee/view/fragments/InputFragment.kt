package com.example.sbertrainee.view.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.sbertrainee.R
import com.example.sbertrainee.App
import com.example.sbertrainee.databinding.FragmentInputBinding
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.presenter.InputPresenter
import java.lang.IllegalStateException

class InputFragment : Fragment(R.layout.fragment_input), Contract.InputView {

    private var binding: FragmentInputBinding? = null
    private lateinit var inputPresenter: InputPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInputBinding.bind(view)
        init()
        initListeners()
    }

    private fun init() {
        val app = requireActivity().applicationContext as App
        val model = app.model
        inputPresenter = InputPresenter(this, model, resources)
    }

    private fun initListeners() {
        binding?.let {
            it.editTextFullName.doOnTextChanged { text, start, _, _ ->
                inputPresenter.onInputNameChanged(text, start)
            }
            it.editTextFullName.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus)
                    hideSoftInputFromWindow(v)
            }

            it.radioMale.setOnClickListener {
                inputPresenter.onInputGenderMaleChecked()
            }

            it.radioFemale.setOnClickListener {
                inputPresenter.onInputGenderFemaleChecked()
            }

            it.checkBoxHasAlphaAccount.setOnCheckedChangeListener { _, isChecked ->
                inputPresenter.onInputAlphaAccountChecked(isChecked)
            }
            it.checkBoxHasSigmaAccount.setOnCheckedChangeListener { _, isChecked ->
                inputPresenter.onInputSigmaAccountChecked(isChecked)
            }
            it.checkBoxHasComputer.setOnCheckedChangeListener { _, isChecked ->
                inputPresenter.onInputComputerChecked(isChecked)
            }
            it.buttonAddTrainee.setOnClickListener {
                inputPresenter.onAddButtonClicked()
            }
        } ?: throw IllegalStateException("Binding is null in InputFragment")
    }

    private fun hideSoftInputFromWindow(v: View) {
        val inputMethodManager =
            activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(v.windowToken, 0)
    }

    override fun notifyNewTraineeAdded() {
        setFragmentResult(REQUEST_INPUT_TRAINEE, Bundle())
    }

    override fun setInputName(name: String, start: Int) {
        binding?.editTextFullName?.setText(name)
        binding?.editTextFullName?.setSelection(start)
    }

    override fun setEnabledAddButton(value: Boolean) {
        binding?.buttonAddTrainee?.isEnabled = value
    }

    override fun clearInputGender() {
        binding?.radioGroupGender?.clearCheck()
    }

    override fun clearInputAlphaAccount() {
        binding?.checkBoxHasAlphaAccount?.isChecked = false
    }

    override fun clearInputSigmaAccount() {
        binding?.checkBoxHasSigmaAccount?.isChecked = false
    }

    override fun clearInputComputer() {
        binding?.checkBoxHasComputer?.isChecked = false
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val REQUEST_INPUT_TRAINEE = "requestInputTrainee"
    }
}