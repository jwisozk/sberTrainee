package com.example.sbertrainee.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.R
import com.example.sbertrainee.model.TraineeData
import com.example.sbertrainee.presenter.adapter.holder.TraineeViewHolder

class TraineeAdapter(
    var traineeList: List<TraineeData>
) : RecyclerView.Adapter<TraineeViewHolder>() {

    fun submitList(newTraineeList: List<TraineeData>) {
        traineeList = newTraineeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraineeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TraineeViewHolder(inflater.inflate(R.layout.page, parent, false))
    }

    override fun onBindViewHolder(holderTrainee: TraineeViewHolder, position: Int) {
        val traineeData = traineeList[position]
        holderTrainee.traineeNumberTextView.text = traineeData.id.toString()
        holderTrainee.fullNameSampleInfoTextView.text = traineeData.fullName
        holderTrainee.genderInfoTextView.text = traineeData.gender
        holderTrainee.alphaAccountInfoTextView.updateVisibility(traineeData.hasAlphaAccount)
        holderTrainee.sigmaAccountInfoTextView.updateVisibility(traineeData.hasSigmaAccount)
        holderTrainee.workComputerInfoTextView.updateVisibility(traineeData.hasComputer)
    }

    override fun getItemCount(): Int = traineeList.size

    private fun View.updateVisibility(isVisible: Boolean) {
       when (isVisible) {
            true -> this.visibility = View.VISIBLE
            false -> this.visibility = View.GONE
        }
    }
}