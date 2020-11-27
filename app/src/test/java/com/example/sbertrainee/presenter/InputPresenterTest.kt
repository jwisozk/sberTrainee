package com.example.sbertrainee.presenter

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputPresenterTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var presenter: InputPresenter
    private lateinit var model: Model
    @RelaxedMockK
    private lateinit var view: Contract.InputView
    @RelaxedMockK
    private lateinit var resources: Resources

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        model = Model()
        presenter = InputPresenter(view, model, resources)
    }

    @Test
    fun `onTextChanged if argument has valid characters return fullName`() {
        presenter.onTextChanged(STR_TEST_FULL_NAME)
        val value = model.trainee?.fullName
        Truth.assertThat(value).isEqualTo(STR_TEST_FULL_NAME)
    }

    @Test
    fun `onTextChanged if argument has not valid characters return null`() {
        presenter.onTextChanged(STR_TEST_NO_VALID_FULL_NAME)
        val value = model.trainee?.fullName
        Truth.assertThat(value).isEqualTo(null)
    }

    @Test
    fun `onEndIconClicked if call return empty fullName`() {
        presenter.onEndIconClicked()
        val value = model.trainee?.fullName
        Truth.assertThat(value).isEqualTo(STR_TEST_EMPTY)
    }

    @Test
    fun `onHasAlphaCheckedChange if argument true return true`() {
        presenter.onHasAlphaCheckedChange(BOOLEAN_TRUE)
        val value = model.trainee?.hasAlphaAccount
        Truth.assertThat(value).isEqualTo(BOOLEAN_TRUE)
    }

    @Test
    fun `onHasSigmaCheckedChange if argument true return true`() {
        presenter.onHasSigmaCheckedChange(BOOLEAN_TRUE)
        val value = model.trainee?.hasSigmaAccount
        Truth.assertThat(value).isEqualTo(BOOLEAN_TRUE)
    }

    @Test
    fun `onHasComputerCheckedChange if argument true return true`() {
        presenter.onHasComputerCheckedChange(BOOLEAN_TRUE)
        val value = model.trainee?.hasComputer
        Truth.assertThat(value).isEqualTo(BOOLEAN_TRUE)
    }

    @Test
    fun `onAddButtonClicked if call return true`() {
        model.setFullName(STR_TEST_FULL_NAME)
        model.setGender(STR_TEST_GENDER)
        presenter.onAddButtonClicked()
        val value = model.isAddedViewPagerFragmentLiveData.value
        Truth.assertThat(value).isEqualTo(BOOLEAN_TRUE)
    }

    companion object {
        private const val STR_TEST_FULL_NAME = "Иванов Иван Иваныч"
        private const val STR_TEST_NO_VALID_FULL_NAME = "@<>:.,"
        private const val STR_TEST_GENDER = "м"
        private const val BOOLEAN_TRUE = true
        private const val STR_TEST_EMPTY = ""
    }
}