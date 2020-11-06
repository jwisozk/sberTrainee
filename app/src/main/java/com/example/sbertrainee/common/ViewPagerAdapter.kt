package com.example.sbertrainee.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.R
import com.example.sbertrainee.model.TraineesCell
import kotlinx.android.synthetic.main.trainee_fragment.view.*

class ViewPagerAdapter(
    var traineeList: List<TraineesCell>,
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<ViewHolder>() {

    fun submitList(newTraineeList: List<TraineesCell>) {
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
    fun bind(cell: TraineesCell) {
        itemView.traineeNumberTextView.text = cell.id.toString()
        itemView.fullNameSampleInfoTextView.text = cell.fullName
        itemView.genderInfoTextView.text = cell.gender
        Util.changeVisible(itemView.alphaAccountInfoTextView, cell.hasAlphaAccount)
        Util.changeVisible(itemView.sigmaAccountInfoTextView, cell.hasSigmaAccount)
        Util.changeVisible(itemView.workComputerInfoTextView, cell.hasComputer)
    }
}