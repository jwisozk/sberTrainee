package com.example.sbertrainee.presenter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.common.Util
import com.example.sbertrainee.model.TraineeData
import kotlinx.android.synthetic.main.trainee_fragment.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(traineeData: TraineeData) {
        itemView.traineeNumberTextView.text = traineeData.id.toString()
        itemView.fullNameSampleInfoTextView.text = traineeData.fullName
        itemView.genderInfoTextView.text = traineeData.gender
        Util.changeVisible(itemView.alphaAccountInfoTextView, traineeData.hasAlphaAccount)
        Util.changeVisible(itemView.sigmaAccountInfoTextView, traineeData.hasSigmaAccount)
        Util.changeVisible(itemView.workComputerInfoTextView, traineeData.hasComputer)
    }
}