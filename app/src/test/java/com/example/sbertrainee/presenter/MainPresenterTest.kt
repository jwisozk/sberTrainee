package com.example.sbertrainee.presenter

import android.graphics.Rect
import android.view.MotionEvent
import android.widget.EditText
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.example.sbertrainee.model.Model
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainPresenterTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var presenter: MainPresenter
    private lateinit var model: Model
    @RelaxedMockK
    private lateinit var fragmentManager: FragmentManager
    @RelaxedMockK
    private lateinit var viewLifecycleOwner: LifecycleOwner
    @RelaxedMockK
    private lateinit var event: MotionEvent
    @RelaxedMockK
    private lateinit var editText: EditText

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        model = Model()
        presenter = MainPresenter(model, fragmentManager, viewLifecycleOwner)
    }

    @Test
    fun `onDispatchTouchEvent check if that call clearFocus were performed`() {
        val slot = slot<Rect>()
        every { editText.requestFocus()} returns BOOLEAN_TRUE
        every { editText.getGlobalVisibleRect(capture(slot))} returns BOOLEAN_TRUE
        every { event.action} returns MotionEvent.ACTION_DOWN
        every { event.rawX} returns FLOAT_RANDOM
        every { event.rawY} returns FLOAT_RANDOM
        every { editText.clearFocus() } returns Unit
        every { editText.hasFocus() } returns BOOLEAN_TRUE
        presenter.onDispatchTouchEvent(event, editText)
        verify { editText.clearFocus() }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    companion object {
        private const val BOOLEAN_TRUE = true
        private const val FLOAT_RANDOM = 0f
    }
}