package com.example.sbertrainee.view.activity

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
import com.example.sbertrainee.databinding.ActivityMainBinding
import com.example.sbertrainee.view.activity.util.KeyboardResetByClickOutside


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var keyboardResetByClickOutside: KeyboardResetByClickOutside

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let {
            val view = it.root
            setContentView(view)
            init()
        }
    }

    private fun init() {
        keyboardResetByClickOutside = KeyboardResetByClickOutside()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        keyboardResetByClickOutside.onDispatchTouchEvent(event, currentFocus)
        return super.dispatchTouchEvent(event)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}