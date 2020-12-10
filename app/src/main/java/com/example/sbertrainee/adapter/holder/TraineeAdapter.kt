package com.example.sbertrainee.adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.R
import com.example.sbertrainee.model.Trainee
import com.example.sbertrainee.adapter.holder.holder.TraineeViewHolder

class TraineeAdapter(
    private var traineeList: List<Trainee>
) : RecyclerView.Adapter<TraineeViewHolder>() {

    fun submitList(newTraineeList: List<Trainee>) {
        traineeList = newTraineeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraineeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TraineeViewHolder(inflater.inflate(R.layout.list_item_trainee, parent, false))
    }

    override fun onBindViewHolder(holderTrainee: TraineeViewHolder, position: Int) {
        val trainee = traineeList[position]
        holderTrainee.traineeNumberTextView?.text = trainee.id.toString()
        holderTrainee.fullNameSampleInfoTextView?.text = trainee.fullName
        holderTrainee.genderInfoTextView?.text = trainee.gender
        updateVisibility(holderTrainee.alphaAccountInfoTextView, trainee.hasAlphaAccount)
        updateVisibility(holderTrainee.sigmaAccountInfoTextView, trainee.hasSigmaAccount)
        updateVisibility(holderTrainee.workComputerInfoTextView, trainee.hasComputer)
    }

    override fun getItemCount(): Int =
        traineeList.size

    private fun updateVisibility(view: View?, isVisible: Boolean) {
        view?.visibility = when (isVisible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
}