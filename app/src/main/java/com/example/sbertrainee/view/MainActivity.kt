package com.example.sbertrainee.view

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import com.example.sbertrainee.Constants
import com.example.sbertrainee.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var keyboardResetByClickOutside: KeyboardResetByClickOutside

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        changeLayoutConfiguration(this.resources.configuration.orientation)
        init()
    }

    private fun init() {
        keyboardResetByClickOutside = KeyboardResetByClickOutside()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        keyboardResetByClickOutside.onDispatchTouchEvent(event, currentFocus)
        return super.dispatchTouchEvent(event)
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
                rightMargin = convertDpToPixel()
            }
            binding.fragmentTraineeCatalogContainer.updateLayoutParams<LinearLayout.LayoutParams> {
                topMargin = Constants.FRAGMENT_MARGIN_ZERO
                leftMargin = convertDpToPixel()
            }
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rootLayout.orientation = LinearLayout.VERTICAL
            binding.fragmentInputContainer.updateLayoutParams<LinearLayout.LayoutParams> {
                bottomMargin = convertDpToPixel()
                rightMargin = Constants.FRAGMENT_MARGIN_ZERO
            }
            binding.fragmentTraineeCatalogContainer.updateLayoutParams<LinearLayout.LayoutParams> {
                topMargin = convertDpToPixel()
                leftMargin = Constants.FRAGMENT_MARGIN_ZERO
            }
        }
    }

    private fun convertDpToPixel(): Int {
        val dp = Constants.FRAGMENT_MARGIN_VALUE
        val metrics = resources.displayMetrics
        return (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    private class KeyboardResetByClickOutside {
        fun onDispatchTouchEvent(event: MotionEvent, currentFocus: View?) {
            if (event.action == MotionEvent.ACTION_DOWN) {
                val v: View? = currentFocus
                if (v is EditText) {
                    val outRect = Rect()
                    v.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        v.clearFocus()
                    }
                }
            }
        }
    }
}