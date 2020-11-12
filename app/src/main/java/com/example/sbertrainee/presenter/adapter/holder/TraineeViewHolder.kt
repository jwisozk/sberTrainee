package com.example.sbertrainee.presenter.adapter.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.trainee_fragment.view.*

class TraineeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val traineeNumberTextView: TextView = itemView.traineeNumberTextView
    val fullNameSampleInfoTextView: TextView = itemView.fullNameSampleInfoTextView
    val genderInfoTextView: TextView = itemView.genderInfoTextView
    val alphaAccountInfoTextView: TextView = itemView.alphaAccountInfoTextView
    val sigmaAccountInfoTextView: TextView = itemView.sigmaAccountInfoTextView
    val workComputerInfoTextView: TextView = itemView.workComputerInfoTextView
}