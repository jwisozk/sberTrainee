package com.example.sbertrainee.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sbertrainee.*

class MainActivity : AppCompatActivity() {

//    private lateinit var traineePresenter: TraineePresenter
//    private val simpleTextWatcher = object : SimpleTextWatcher() {
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            traineePresenter.onTextChanged(s)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentInputContainer, InputFragment.newInstance())
            .commitNow()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentViewPagerContainer, ViewPagerFragment.newInstance())
            .commitNow()
//        init()
//        addListeners()
    }

//    fun addViewPagerFragment() {
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragmentViewPagerContainer, ViewPagerFragment.newInstance())
//            .commitNow()
//    }


//    private fun init() {
//        val model = App.model
//        traineePresenter = TraineePresenter(this, model)
//        viewPager.adapter = TraineeAdapter(traineePresenter.getTraineeList())
//    }
//
//    private fun addListeners() {
//        editTextFullName.addTextChangedListener(simpleTextWatcher)
//        radioGroupGender.setOnCheckedChangeListener { _, checkedId ->
//            traineePresenter.onGenderCheckedChange(checkedId)
//        }
//        checkBoxHasAlphaAccount.setOnCheckedChangeListener { _, isChecked ->
//            traineePresenter.onHasAlphaCheckedChange(isChecked)
//        }
//        checkBoxHasSigmaAccount.setOnCheckedChangeListener { _, isChecked ->
//            traineePresenter.onHasSigmaCheckedChange(isChecked)
//        }
//        checkBoxHasComputer.setOnCheckedChangeListener { _, isChecked ->
//            traineePresenter.onHasComputerCheckedChange(isChecked)
//        }
//        buttonAddTrainee.setOnClickListener {
//            traineePresenter.onAddButtonClicked()
//        }
//    }
//
//    override fun showTrainee(traineeList: List<TraineeData>) {
//        val viewPagerAdapter = viewPager.adapter as TraineeAdapter
//        viewPagerAdapter.submitList(traineeList)
//        viewPager.currentItem = traineePresenter.getCurrentItemViewPager()
//    }
//
//    override fun showErrorMessage(errorId: Int) {
//        Toast.makeText(this, getString(errorId), Toast.LENGTH_LONG).show()
//    }
//
//    override fun clear() {
//        editTextFullName.editableText.clear()
//        editTextFullName.clearFocus()
//        radioGroupGender.clearCheck()
//        checkBoxHasAlphaAccount.isChecked = false
//        checkBoxHasSigmaAccount.isChecked = false
//        checkBoxHasComputer.isChecked = false
//    }
}