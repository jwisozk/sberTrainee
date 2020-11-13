package com.example.sbertrainee.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sbertrainee.*
import com.example.sbertrainee.common.App
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.common.SimpleTextWatcher
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import com.example.sbertrainee.model.TraineeData
import com.example.sbertrainee.presenter.TraineePresenter
import kotlinx.android.synthetic.main.activity_main.*

class TraineeActivity : AppCompatActivity(), Contract.View {

    private lateinit var traineePresenter: TraineePresenter
    private val simpleTextWatcher = object : SimpleTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            traineePresenter.onTextChanged(s)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        addListeners()
    }

    private fun init() {
        val model = App.model
        traineePresenter = TraineePresenter(this, model)
        viewPager.adapter = TraineeAdapter(traineePresenter.getTraineeList())
    }

    private fun addListeners() {
        editTextFullName.addTextChangedListener(simpleTextWatcher)
        radioGroupGender.setOnCheckedChangeListener { _, checkedId ->
            traineePresenter.onGenderCheckedChange(checkedId)
        }
        checkBoxHasAlphaAccount.setOnCheckedChangeListener { _, isChecked ->
            traineePresenter.onHasAlphaCheckedChange(isChecked)
        }
        checkBoxHasSigmaAccount.setOnCheckedChangeListener { _, isChecked ->
            traineePresenter.onHasSigmaCheckedChange(isChecked)
        }
        checkBoxHasComputer.setOnCheckedChangeListener { _, isChecked ->
            traineePresenter.onHasComputerCheckedChange(isChecked)
        }
        buttonAddTrainee.setOnClickListener {
            traineePresenter.onAddButtonClicked()
        }
    }

    override fun showTrainee(traineeList: List<TraineeData>) {
        val viewPagerAdapter = viewPager.adapter as TraineeAdapter
        viewPagerAdapter.submitList(traineeList)
        viewPager.currentItem = traineePresenter.getCurrentItemViewPager()
    }

    override fun showErrorMessage(errorId: Int) {
        Toast.makeText(this, getString(errorId), Toast.LENGTH_LONG).show()
    }

    override fun clear() {
        editTextFullName.editableText.clear()
        editTextFullName.clearFocus()
        radioGroupGender.clearCheck()
        checkBoxHasAlphaAccount.isChecked = false
        checkBoxHasSigmaAccount.isChecked = false
        checkBoxHasComputer.isChecked = false
    }
}