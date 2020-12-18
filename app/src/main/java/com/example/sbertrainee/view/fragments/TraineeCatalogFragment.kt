package com.example.sbertrainee.view.fragments

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.example.sbertrainee.R
import com.example.sbertrainee.App
import com.example.sbertrainee.databinding.FragmentTraineeCatalogBinding
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Trainee
import com.example.sbertrainee.presenter.TraineeCatalogPresenter
import com.example.sbertrainee.adapter.holder.TraineeAdapter
import com.example.sbertrainee.view.activity.MainActivity
import com.example.sbertrainee.view.fragments.constants.Constants
import com.example.sbertrainee.view.fragments.util.AnimatorListener
import com.example.sbertrainee.view.fragments.util.DepthPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IllegalStateException

class TraineeCatalogFragment : Fragment(R.layout.fragment_trainee_catalog),
    Contract.TraineeCatalogView {

    private lateinit var traineeCatalogPresenter: TraineeCatalogPresenter
    private var binding: FragmentTraineeCatalogBinding? = null
    private var traineeAdapter: TraineeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTraineeCatalogBinding.bind(view)
        init()
    }

    private fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        traineeCatalogPresenter = TraineeCatalogPresenter(this, model, requireContext().filesDir)
        binding?.let {
            it.viewPager.setPageTransformer(DepthPageTransformer())
            it.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    traineeCatalogPresenter.onItemPositionSelected(position)
                }
            })
            TabLayoutMediator(it.tabLayout, it.viewPager) { _, _ ->
            }.attach()
            setFragmentResultListener(Constants.REQUEST_INPUT_TRAINEE) { _, _ ->
                setVisibilityFragmentView(View.VISIBLE)
                traineeCatalogPresenter.refreshTraineeList()
            }
        } ?: throw IllegalStateException("Binding is null in TraineeCatalogFragment")
    }

    override fun setVisibilityFragmentView(value: Int) {
        binding?.root?.visibility = value
    }

    override fun setTraineeList(traineeList: List<Trainee>) {
        traineeAdapter = TraineeAdapter(traineeList, object : TraineeAdapter.Listener {
            override fun onRemoveTraineeClickListener(lottieAnimationView: LottieAnimationView) {
                lottieAnimationView.playAnimation()
            }

            override fun addAnimatorListener(lottieAnimationView: LottieAnimationView): AnimatorListener {
                return object : AnimatorListener() {
                    override fun onAnimationEnd(animation: Animator?) {
                        lottieAnimationView.progress = PROGRESS_ANIMATION
                        traineeCatalogPresenter.onRemoveButtonClicked()
                    }
                }
            }
        })
        binding?.viewPager?.adapter = traineeAdapter
    }

    override fun updateTraineeList(traineeList: List<Trainee>) {
        traineeAdapter?.submitList(traineeList)
    }

    override fun setSelectedItemPosition(position: Int) {
        binding?.viewPager?.setCurrentItem(position, false)
    }

    override fun onStop() {
        super.onStop()
        traineeCatalogPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val PROGRESS_ANIMATION = 0f
    }
}