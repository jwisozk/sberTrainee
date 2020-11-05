package com.example.sbertrainee.mvp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sbertrainee.*
import androidx.viewpager2.widget.ViewPager2 as ViewPager

class TraineeActivity : AppCompatActivity(), Contract.View {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: TraineeSectionsPagerAdapter
    private lateinit var traineeList: MutableList<TraineeData>

    companion object {
        private const val GENDER_MAN = "м"
        private const val GENDER_WOMAN = "ж"
    }

    private var traineePresenter: TraineePresenter? = null
    private lateinit var editTextFullName: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var checkBoxHasAlphaAccount: CheckBox
    private lateinit var checkBoxHasSigmaAccount: CheckBox
    private lateinit var checkBoxHasComputer: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        traineeList = ArrayList()
        val viewPagerAdapter = ViewPagerAdapter(traineeList, layoutInflater)
//        pagerAdapter = TraineeSectionsPagerAdapter(this)
        viewPager = findViewById(R.id.traineeViewPager)
        viewPager.adapter = viewPagerAdapter

        editTextFullName = findViewById(R.id.fullNameEditText)
        radioGroupGender = findViewById(R.id.radioGroup)
        checkBoxHasAlphaAccount = findViewById(R.id.alphaAccountCheckBox)
        checkBoxHasSigmaAccount = findViewById(R.id.sigmaAccountCheckBox)
        checkBoxHasComputer = findViewById(R.id.computerProvidedCheckBox)

        val addButton: Button = findViewById(R.id.addTraineeButton)
        addButton.setOnClickListener {
            traineePresenter?.onButtonWasClicked()
        }
//        val radioButton = findViewById<RadioButton>(R.id.radioGenderMan)
//        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
//        radioGroup.check
//        radioGroup.setOnCheckedChangeListener { _, checkedId ->
//            val radio: RadioButton = findViewById(checkedId)
//            when (radio)
//                radio
//        }
        traineePresenter = TraineePresenter().apply {
            attachView(this@TraineeActivity)
        }


//        presenter.viewIsReady()
    }

    override fun getTraineeData(): TraineeData {
        val gender = when(radioGroupGender.checkedRadioButtonId) {
            R.id.radioGenderMan -> GENDER_MAN
            R.id.radioGenderWoman -> GENDER_WOMAN
            else -> null
        }
        return TraineeData(
            editTextFullName.text.toString(),
            gender,
            checkBoxHasAlphaAccount.isChecked,
            checkBoxHasSigmaAccount.isChecked,
            checkBoxHasComputer.isChecked
        )
    }

    override fun showTraineeInfo(traineeData: TraineeData) {
        traineeList.add(traineeData)
//        val viewPagerAdapter = ViewPagerAdapter(traineeList, layoutInflater)
//        viewPager.adapter = viewPagerAdapter
//        (viewPager.adapter as ViewPagerAdapter).addTrainee(traineeData)
        (viewPager.adapter as ViewPagerAdapter).submitList(traineeList)
        viewPager.currentItem = traineeList.size - 1
    }

    override fun showErrorMessage(msgErrorId: Int) {
        Log.d(this.toString(), "click")
        Toast.makeText(this, getString(msgErrorId), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        traineePresenter?.detachView()
    }
}