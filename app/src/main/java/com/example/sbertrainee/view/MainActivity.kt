package com.example.sbertrainee.view

import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import com.example.sbertrainee.App
import com.example.sbertrainee.Constants
import com.example.sbertrainee.databinding.ActivityMainBinding
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.presenter.MainPresenter


class MainActivity : AppCompatActivity(), Contract.MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        changeLayoutConfiguration(this.resources.configuration.orientation)
        init()
    }

    private fun init() {
        val app = applicationContext as App
        val model = app.model
        mainPresenter = MainPresenter(this, model, supportFragmentManager, this)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        mainPresenter.onDispatchTouchEvent(event, currentFocus)
        return super.dispatchTouchEvent(event)
    }

    override fun setViewPagerFragmentVisible() {
        binding.fragmentTraineeCatalogContainer.visibility = View.VISIBLE
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        changeLayoutConfiguration(newConfig.orientation)
    }

    private fun changeLayoutConfiguration(orientation: Int) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rootLayout.orientation = LinearLayout.HORIZONTAL
            binding.fragmentInputContainer.updateLayoutParams<LinearLayout.LayoutParams> {
                bottomMargin = Constants.FRAGMENT_MARGIN_ZERO
                rightMargin = convertDpToPixel(Constants.FRAGMENT_MARGIN_VALUE)
            }
            binding.fragmentTraineeCatalogContainer.updateLayoutParams<LinearLayout.LayoutParams> {
                topMargin = Constants.FRAGMENT_MARGIN_ZERO
                leftMargin = convertDpToPixel(Constants.FRAGMENT_MARGIN_VALUE)
            }
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rootLayout.orientation = LinearLayout.VERTICAL
            binding.fragmentInputContainer.updateLayoutParams<LinearLayout.LayoutParams> {
                bottomMargin = convertDpToPixel(Constants.FRAGMENT_MARGIN_VALUE)
                rightMargin = Constants.FRAGMENT_MARGIN_ZERO
            }
            binding.fragmentTraineeCatalogContainer.updateLayoutParams<LinearLayout.LayoutParams> {
                topMargin = convertDpToPixel(Constants.FRAGMENT_MARGIN_VALUE)
                leftMargin = Constants.FRAGMENT_MARGIN_ZERO
            }
        }
    }

    private fun convertDpToPixel(dp: Int): Int {
        return run {
            val resources = resources
            val metrics = resources.displayMetrics
            (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }
    }
}