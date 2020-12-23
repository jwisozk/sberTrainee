package com.example.sbertrainee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.sbertrainee.R
import com.example.sbertrainee.adapter.holder.TraineeViewHolder
import com.example.sbertrainee.model.Trainee
import com.example.sbertrainee.view.fragments.util.AnimatorListener

class TraineeAdapter(
    private var traineeList: List<Trainee>,
    private val listener: Listener
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
        with(holderTrainee.binding) {
            traineeNumberTextView.text = trainee.id.toString()
            fullNameSampleInfoTextView.text = trainee.fullName
            genderInfoTextView.text = trainee.gender
            updateVisibility(alphaAccountInfoTextView, trainee.hasAlphaAccount)
            updateVisibility(sigmaAccountInfoTextView, trainee.hasSigmaAccount)
            updateVisibility(workComputerInfoTextView, trainee.hasComputer)
            removeTraineeLottieAnimationView.removeAllAnimatorListeners()
            removeTraineeLottieAnimationView.setOnClickListener {
                listener.onRemoveTraineeClicked(
                    removeTraineeLottieAnimationView
                )
            }
            removeTraineeLottieAnimationView.addAnimatorListener(
                listener.setAnimatorListener(
                    removeTraineeLottieAnimationView,
                    trainee
                )
            )
        }
    }

    override fun getItemCount(): Int =
        traineeList.size

    private fun updateVisibility(view: View?, isVisible: Boolean) {
        view?.visibility = when (isVisible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    interface Listener {
        fun setAnimatorListener(
            lottieAnimationView: LottieAnimationView,
            trainee: Trainee
        ): AnimatorListener

        fun onRemoveTraineeClicked(lottieAnimationView: LottieAnimationView)
    }
}