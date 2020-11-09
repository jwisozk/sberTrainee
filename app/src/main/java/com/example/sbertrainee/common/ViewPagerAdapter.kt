package com.example.sbertrainee.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.R
import com.example.sbertrainee.model.TraineeData
import kotlinx.android.synthetic.main.trainee_fragment.view.*

class ViewPagerAdapter(
    var traineeList: List<TraineeData>,
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<ViewHolder>() {

    fun submitList(newTraineeList: List<TraineeData>) {
        traineeList = newTraineeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.trainee_fragment, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(traineeList[position])

    override fun getItemCount(): Int = traineeList.size
}

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