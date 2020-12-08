package com.example.sbertrainee.presenter

import android.content.res.Resources
import com.example.sbertrainee.R
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.Trainee
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class InputPresenterTest {

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
        presenter.traineeTmp = TRAINEE
    }

    @Test
    fun `onInputNameChanged if argument has valid characters return fullName`() {
        presenter.onInputNameChanged(STR_TEST_FULL_NAME)
        val fullName = presenter.traineeTmp?.fullName
        Truth.assertThat(fullName).isEqualTo(STR_TEST_FULL_NAME)
    }

    @Test
    fun `onInputNameChanged if argument has not valid characters return fullName`() {
        presenter.onInputNameChanged(STR_TEST_NO_VALID_FULL_NAME)
        val fullName = presenter.traineeTmp?.fullName
        Truth.assertThat(fullName).isEqualTo(STR_TEST_FULL_NAME)
    }

    @Test
    fun `onClearButtonClicked if call return empty fullName`() {
        presenter.onClearButtonClicked()
        val fullName = presenter.traineeTmp?.fullName
        Truth.assertThat(fullName).isEqualTo(STR_TEST_EMPTY)
    }

    @Test
    fun `onInputAlphaAccountChecked if argument true return true`() {
        presenter.onInputAlphaAccountChecked(BOOLEAN_TRUE)
        val hasAlphaAccount = presenter.traineeTmp?.hasAlphaAccount
        Truth.assertThat(hasAlphaAccount).isEqualTo(BOOLEAN_TRUE)
    }

    @Test
    fun `onInputSigmaAccountChecked if argument true return true`() {
        presenter.onInputSigmaAccountChecked(BOOLEAN_TRUE)
        val hasSigmaAccount = presenter.traineeTmp?.hasSigmaAccount
        Truth.assertThat(hasSigmaAccount).isEqualTo(BOOLEAN_TRUE)
    }

    @Test
    fun `onInputComputerChecked if argument true return true`() {
        presenter.onInputComputerChecked(BOOLEAN_TRUE)
        val hasComputer = presenter.traineeTmp?.hasComputer
        Truth.assertThat(hasComputer).isEqualTo(BOOLEAN_TRUE)
    }

    @Test
    fun `onInputGenderMaleChecked if call return male sign`() {
        every { resources.getString(R.string.gender_male)} returns STR_TEST_GENDER_MALE
        presenter.onInputGenderMaleChecked()
        val genderMale = presenter.traineeTmp?.gender
        Truth.assertThat(genderMale).isEqualTo(STR_TEST_GENDER_MALE)
    }

    @Test
    fun `onInputGenderFemaleChecked if call return female sign`() {
        every { resources.getString(R.string.gender_female)} returns STR_TEST_GENDER_FEMALE
        presenter.onInputGenderFemaleChecked()
        val genderFemale = presenter.traineeTmp?.gender
        Truth.assertThat(genderFemale).isEqualTo(STR_TEST_GENDER_FEMALE)
    }

    @Test
    fun `isDataEnough if trainee has fullName and gender return true`() {
        val isDataEnough = presenter.isDataEnough()
        Truth.assertThat(isDataEnough).isEqualTo(BOOLEAN_TRUE)
    }
//
    @Test
    fun `isDataEnough if trainee has only fullName return false`() {
        presenter.traineeTmp = null
        presenter.onInputNameChanged(STR_TEST_FULL_NAME)
        val isDataEnough = presenter.isDataEnough()
        Truth.assertThat(isDataEnough).isEqualTo(BOOLEAN_FALSE)
    }

    @Test
    fun `isDataEnough if trainee has only gender return false`() {
        presenter.traineeTmp = null
        presenter.onInputGenderMaleChecked()
        val isDataEnough = presenter.isDataEnough()
        Truth.assertThat(isDataEnough).isEqualTo(BOOLEAN_FALSE)
    }

    @Test
    fun `onAddButtonClicked if call return old size traineeList and Trainee`() {
        val oldSizeTraineeList = model.getTraineeList().size
        presenter.onAddButtonClicked()
        val traineeList = model.getTraineeList()
        Truth.assertThat(oldSizeTraineeList).isEqualTo(0)
        Truth.assertThat(traineeList[0]).isEqualTo(TRAINEE)
    }

    companion object {
        private const val STR_TEST_FULL_NAME = "Иванов Иван Иваныч"
        private const val STR_TEST_NO_VALID_FULL_NAME = "@<>:.,"
        private const val STR_TEST_GENDER_MALE = "\u2642"
        private const val STR_TEST_GENDER_FEMALE = "\u2640"
        private const val BOOLEAN_TRUE = true
        private const val BOOLEAN_FALSE = false
        private const val STR_TEST_EMPTY = ""
        private val TRAINEE = Trainee().apply {
            id = 1
            fullName = STR_TEST_FULL_NAME
            gender = STR_TEST_GENDER_MALE
            hasAlphaAccount = BOOLEAN_TRUE
            hasSigmaAccount = BOOLEAN_TRUE
            hasComputer = BOOLEAN_TRUE
        }
    }
}