package com.example.sbertrainee.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.google.android.material.tabs.TabLayout
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewPagerPresenterTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var presenter: ViewPagerPresenter
    private lateinit var model: Model
    private lateinit var tab: TabLayout.Tab
    @RelaxedMockK
    private lateinit var view: Contract.ViewPagerView
    @RelaxedMockK
    private lateinit var viewLifecycleOwner: LifecycleOwner


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        model = Model()
        presenter = ViewPagerPresenter(view, model, viewLifecycleOwner)
        tab = TabLayout.Tab()
    }

    @Test
    fun `onTabLayoutMediatorAttach if call return trainee surname with id`() {
        model.setFullName(STR_TEST_FULL_NAME)
        val trainee = model.newTrainee()
        model.addTrainee(trainee!!)
        presenter.onTabLayoutMediatorAttach(tab, POSITION)
        assertThat(tab.text).isEqualTo("${(POSITION)}.${STR_TEST_FULL_NAME.takeWhile { it.isLetter() }}")
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    companion object {
        private const val STR_TEST_FULL_NAME = "Иванов Иван Иваныч"
        private const val POSITION = 1
    }
}