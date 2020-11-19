package com.example.sbertrainee.presenter

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.sbertrainee.common.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.TraineeData
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import kotlinx.android.synthetic.main.fragment_view_pager.*
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerPresenter(
    private val view: Contract.ViewPagerView,
    private val model: Model,
    private val viewLifecycleOwner: LifecycleOwner
) : Contract.ViewPagerPresenter {

    private val traineeAdapter: TraineeAdapter

    init {
        val traineeList = model.traineeListLiveData.value ?: ArrayList()
        traineeAdapter = TraineeAdapter(traineeList)
        view.setAdapter(traineeAdapter)
        model.traineeListLiveData.observe(viewLifecycleOwner) { value ->
            if (value == null)
                return@observe
            showLastTrainee(value)
            Log.d("ViewPagerPresenter", "Here")
        }
    }

    private fun showLastTrainee(traineeList: List<TraineeData>) {
        traineeAdapter.submitList(traineeList)
        view.setCurrentPage(traineeList.size - 1)
    }
}