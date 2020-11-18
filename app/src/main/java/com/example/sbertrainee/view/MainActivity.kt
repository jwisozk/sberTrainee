package com.example.sbertrainee.view

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.sbertrainee.R
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.presenter.MainPresenter


class MainActivity : AppCompatActivity(), Contract.MainView {

    private lateinit var mainPresenter: MainPresenter
    
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
        

        init()
//        addListeners()
    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        mainPresenter.onDispatchTouchEvent(event, currentFocus)
        return super.dispatchTouchEvent(event)
    }

//    fun hideKeyboard(view: View) {
//        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//    }

//    fun addViewPagerFragment() {
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragmentViewPagerContainer, ViewPagerFragment.newInstance())
//            .commitNow()
//    }


    private fun init() {

//        val model = App.model
        mainPresenter = MainPresenter(this)
//        viewPager.adapter = TraineeAdapter(traineePresenter.getTraineeList())
    }
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