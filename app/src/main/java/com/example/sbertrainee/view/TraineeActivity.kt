package com.example.sbertrainee.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sbertrainee.*
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.common.SimpleTextWatcher
import com.example.sbertrainee.common.ViewPagerAdapter
import com.example.sbertrainee.model.TraineeData
import com.example.sbertrainee.model.TraineeViewModel
import com.example.sbertrainee.presenter.TraineePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.trainee_fragment.*

class TraineeActivity : AppCompatActivity(), Contract.View {

    private lateinit var traineePresenter: TraineePresenter
    private lateinit var viewModel: TraineeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        addListeners()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(TraineeViewModel::class.java)
        viewPager.adapter = ViewPagerAdapter(viewModel.getTraineeList(), layoutInflater)
        traineePresenter = TraineePresenter(this, viewModel)
    }

    private fun addListeners() {
        editTextFullName.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                traineePresenter.onTextChanged(s)
            }
        })
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
            traineePresenter.onButtonClicked()
        }
    }

    override fun showTrainee(traineeData: TraineeData) {
        viewModel.addTrainee(traineeData)
        val traineeList = viewModel.getTraineeList()
        val viewPagerAdapter = viewPager.adapter as ViewPagerAdapter
        viewPagerAdapter.submitList(traineeList)
        viewPager.currentItem = traineeList.size - 1
    }

    override fun showErrorMessage(errorId: Int) {
        Toast.makeText(this, getString(errorId), Toast.LENGTH_LONG).show()
    }

    override fun clear() {
        editTextFullName.editableText.clear()
        radioGroupGender.clearCheck()
        checkBoxHasAlphaAccount.isChecked = false
        checkBoxHasSigmaAccount.isChecked = false
        checkBoxHasComputer.isChecked = false
    }

    override fun onDestroy() {
        super.onDestroy()
        traineePresenter.detachView()
    }
}