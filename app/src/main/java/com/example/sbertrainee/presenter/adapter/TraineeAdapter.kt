package com.example.sbertrainee.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.R
import com.example.sbertrainee.model.Trainee
import com.example.sbertrainee.presenter.adapter.holder.TraineeViewHolder

class TraineeAdapter(
    var traineeList: List<Trainee>
) : RecyclerView.Adapter<TraineeViewHolder>() {

    fun submitList(newTraineeList: List<Trainee>) {
        traineeList = newTraineeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraineeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TraineeViewHolder(inflater.inflate(R.layout.page, parent, false))
    }

    override fun onBindViewHolder(holderTrainee: TraineeViewHolder, position: Int) {
        val trainee = traineeList[position]
        holderTrainee.traineeNumberTextView.text = trainee.id.toString()
        holderTrainee.fullNameSampleInfoTextView.text = trainee.fullName
        holderTrainee.genderInfoTextView.text = trainee.gender
        holderTrainee.alphaAccountInfoTextView.updateVisibility(trainee.hasAlphaAccount)
        holderTrainee.sigmaAccountInfoTextView.updateVisibility(trainee.hasSigmaAccount)
        holderTrainee.workComputerInfoTextView.updateVisibility(trainee.hasComputer)
    }

    override fun getItemCount(): Int = traineeList.size

    private fun View.updateVisibility(isVisible: Boolean) {
        when (isVisible) {
            true -> this.visibility = View.VISIBLE
            false -> this.visibility = View.GONE
        }
    }
}