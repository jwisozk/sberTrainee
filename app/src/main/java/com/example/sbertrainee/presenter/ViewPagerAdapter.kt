package com.example.sbertrainee.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.R
import com.example.sbertrainee.common.Util
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