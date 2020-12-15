package com.example.sbertrainee.view.activity

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
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