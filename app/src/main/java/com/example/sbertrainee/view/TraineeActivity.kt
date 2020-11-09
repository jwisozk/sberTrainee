package com.example.sbertrainee.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sbertrainee.*
import com.example.sbertrainee.common.App
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.common.ViewPagerAdapter
import com.example.sbertrainee.model.TraineeData
import com.example.sbertrainee.presenter.TraineePresenter
import kotlinx.android.synthetic.main.activity_main.*

class TraineeActivity : AppCompatActivity(), Contract.View {

    private var traineePresenter: TraineePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        viewPager.adapter = ViewPagerAdapter(App.traineeList, layoutInflater)
        buttonAddTrainee.setOnClickListener {
            traineePresenter?.onButtonWasClicked()
        }
        traineePresenter = TraineePresenter().apply {
            attachView(this@TraineeActivity)
        }
    }

    override fun getFullName(): String = editTextFullName.text.toString()

    override fun getGenderId(): Int = radioGroupGender.checkedRadioButtonId

    override fun hasAlphaAccount(): Boolean = checkBoxHasAlphaAccount.isChecked

    override fun hasSigmaAccount(): Boolean = checkBoxHasSigmaAccount.isChecked

    override fun hasComputer(): Boolean = checkBoxHasComputer.isChecked

    override fun addTrainee(traineeData: TraineeData) {
        App.traineeList.add(traineeData)
        val viewPagerAdapter = viewPager.adapter as ViewPagerAdapter
        viewPagerAdapter.submitList(App.traineeList)
        viewPager.currentItem = App.traineeList.size - 1
    }

    override fun showErrorMessage(msgErrorId: Int) {
        Toast.makeText(this, getString(msgErrorId), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        traineePresenter?.detachView()
    }
}