package com.example.sbertrainee

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.mvp.TraineeData

class ViewPagerAdapter(
    private var traineeList: List<TraineeData>,
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<ViewHolder>() {

//    fun addTrainee(traineeData: TraineeData) {
//        traineeList.add(traineeData)
//        notifyDataSetChanged()
//    }

    fun submitList(newTraineeList: List<TraineeData>) {
        traineeList = newTraineeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.trainee_fragment, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(traineeList[position], position)

    override fun getItemCount(): Int =
        traineeList.size
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val traineeNumberView = itemView.findViewById<TextView>(R.id.traineeNumberTextView)
    private val fullNameView: TextView = itemView.findViewById(R.id.fullNameSampleInfoTextView)
    private val genderView: TextView  = itemView.findViewById(R.id.genderInfoTextView)
    private val alphaView: View = itemView.findViewById(R.id.alphaAccountInfoTextView)
    private val sigmaView: View = itemView.findViewById(R.id.sigmaAccountInfoTextView)
    private val compView: View = itemView.findViewById(R.id.workComputerInfoTextView)

    fun bind(traineeData: TraineeData, position: Int) {
        traineeNumberView.text = (adapterPosition + 1).toString()
        fullNameView.text = traineeData.fullName
        genderView.text = traineeData.gender
        Log.d("ViewHolder", "position: $position, item: " + traineeNumberView.text.toString())
        changeVisible(alphaView, traineeData.hasAlphaAccount)
        changeVisible(sigmaView, traineeData.hasSigmaAccount)
        changeVisible(compView, traineeData.hasComputer)

        Log.d("ViewHolder", "alpha: ${traineeData.hasAlphaAccount}")
        Log.d("ViewHolder", "sigma: ${traineeData.hasSigmaAccount}")
        Log.d("ViewHolder", "comp: ${traineeData.hasComputer}")
    }

    private fun changeVisible(view: View, isCheck: Boolean) = when(isCheck) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}

