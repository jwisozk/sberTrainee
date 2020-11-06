package com.example.sbertrainee.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.example.sbertrainee.*
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.common.TraineeDiffUtilCallback
import com.example.sbertrainee.common.ViewPagerAdapter
import com.example.sbertrainee.model.TraineesCell
import com.example.sbertrainee.presenter.TraineePresenter
import kotlinx.android.synthetic.main.activity_main.*

class TraineeActivity : AppCompatActivity(), Contract.View {

    private lateinit var traineeList: MutableList<TraineesCell>
    private var traineePresenter: TraineePresenter? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        traineeList = ArrayList()
        viewPager.adapter = ViewPagerAdapter(traineeList, layoutInflater)
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

    override fun addTrainee(cell: TraineesCell) {
//        val newTraineeList = traineeList.toMutableList()
//        newTraineeList.add(cell)
        traineeList.add(cell)
        val viewPagerAdapter = viewPager.adapter as ViewPagerAdapter
//        val oldTraineeList = viewPagerAdapter.traineeList
//        val traineeDiffUtilCallback = TraineeDiffUtilCallback(newTraineeList, oldTraineeList)
//        val traineeDiffResult = DiffUtil.calculateDiff(traineeDiffUtilCallback, false)
//        viewPagerAdapter.submitList(newTraineeList)
        viewPagerAdapter.submitList(traineeList)
//        traineeDiffResult.dispatchUpdatesTo(viewPagerAdapter)
        viewPager.currentItem = traineeList.size - 1
    }

    override fun showErrorMessage(msgErrorId: Int) {
        Toast.makeText(this, getString(msgErrorId), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        traineePresenter?.detachView()
    }
}