package com.example.sbertrainee.presenter.adapter.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.R

class TraineeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val traineeNumberTextView: TextView = itemView.findViewById(R.id.traineeNumberTextView)
    val fullNameSampleInfoTextView: TextView = itemView.findViewById(R.id.fullNameSampleInfoTextView)
    val genderInfoTextView: TextView = itemView.findViewById(R.id.genderInfoTextView)
    val alphaAccountInfoTextView: TextView = itemView.findViewById(R.id.alphaAccountInfoTextView)
    val sigmaAccountInfoTextView: TextView = itemView.findViewById(R.id.sigmaAccountInfoTextView)
    val workComputerInfoTextView: TextView = itemView.findViewById(R.id.workComputerInfoTextView)
}