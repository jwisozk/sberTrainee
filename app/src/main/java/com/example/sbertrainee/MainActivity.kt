package com.example.sbertrainee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.viewpager2.widget.ViewPager2 as ViewPager

class MainActivity : AppCompatActivity(), Contract.View {

    companion object {
        private const val GENDER_MAN = "м"
        private const val GENDER_WOMAN = "ж"
    }

    private var presenter: Presenter? = null
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
        val pagerAdapter = TraineeSectionsPagerAdapter(this)
        val viewPager: ViewPager = findViewById(R.id.traineeViewPager)
        viewPager.adapter = pagerAdapter

        editTextFullName = findViewById(R.id.fullNameEditText)
        radioGroupGender = findViewById(R.id.radioGroup)
        checkBoxHasAlphaAccount = findViewById(R.id.alphaAccountCheckBox)
        checkBoxHasSigmaAccount = findViewById(R.id.sigmaAccountCheckBox)
        checkBoxHasComputer = findViewById(R.id.computerProvidedCheckBox)

        val addButton: Button = findViewById(R.id.addTraineeButton)
        addButton.setOnClickListener {
            presenter?.onButtonWasClicked()
        }
//        val radioButton = findViewById<RadioButton>(R.id.radioGenderMan)
//        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
//        radioGroup.check
//        radioGroup.setOnCheckedChangeListener { _, checkedId ->
//            val radio: RadioButton = findViewById(checkedId)
//            when (radio)
//                radio
//        }
        val model = TraineeViewModel()
        presenter = Presenter(model).apply {
            attachView(this@MainActivity)
            viewIsReady()
        }
//        presenter.attachView(this)
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
            checkBoxHasComputer.isChecked)
    }

    override fun showErrorMessage(msgError: String) {
        Log.d(this.toString(), "click")
        Toast.makeText(this, msgError, Toast.LENGTH_LONG).show()
    }
}