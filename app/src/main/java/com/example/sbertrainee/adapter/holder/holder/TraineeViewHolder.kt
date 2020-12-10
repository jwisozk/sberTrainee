package com.example.sbertrainee.adapter.holder.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.databinding.ListItemTraineeBinding

class TraineeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ListItemTraineeBinding? = null

    init {
        binding = ListItemTraineeBinding.bind(itemView)
    }

    val traineeNumberTextView = binding?.traineeNumberTextView
    val fullNameSampleInfoTextView = binding?.fullNameSampleInfoTextView
    val genderInfoTextView = binding?.genderInfoTextView
    val alphaAccountInfoTextView = binding?.alphaAccountInfoTextView
    val sigmaAccountInfoTextView = binding?.sigmaAccountInfoTextView
    val workComputerInfoTextView = binding?.workComputerInfoTextView
}