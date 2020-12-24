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
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class InputPresenterTest {

    private lateinit var presenter: InputPresenter

    @RelaxedMockK
    private lateinit var model: Model

    @RelaxedMockK
    private lateinit var view: Contract.InputView

    @RelaxedMockK
    private lateinit var resources: Resources

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = InputPresenter(view, model, resources)
        presenter.traineeTmp = TRAINEE
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `onInputNameChanged if argument has valid characters return fullName`() {
        presenter.onInputNameChanged(STR_TEST_FULL_NAME, STR_TEST_FULL_NAME.length)
        val fullName = presenter.traineeTmp?.fullName
        Truth.assertThat(fullName).isEqualTo(STR_TEST_FULL_NAME)
    }

    @Test
    fun `onInputNameChanged if argument has not valid characters return fullName`() {
        presenter.onInputNameChanged(STR_TEST_NO_VALID_FULL_NAME, STR_TEST_FULL_NAME.length)
        val fullName = presenter.traineeTmp?.fullName
        Truth.assertThat(fullName).isEqualTo(STR_TEST_FULL_NAME)
    }

    @Test
    fun `onInputNameChanged if argument is empty string return empty string`() {
        presenter.onInputNameChanged("", 0)
        val fullName = presenter.traineeTmp?.fullName
        Truth.assertThat(fullName).isEqualTo("")
    }

    @Test
    fun `onInputNameChanged if argument is null throw IllegalArgumentException`() {
        try {
            presenter.onInputNameChanged(null, 0)
        } catch (e: IllegalArgumentException) {
            Truth.assertThat(e).hasMessageThat().contains(ILLEGAL_ARGUMENT_EXCEPTION)
        }
    }

    @Test
    fun `onInputGenderMaleChecked if call return male sign`() {
        every { resources.getString(R.string.gender_male) } returns STR_TEST_GENDER_MALE
        presenter.onInputGenderMaleChecked()
        val genderMale = presenter.traineeTmp?.gender
        Truth.assertThat(genderMale).isEqualTo(STR_TEST_GENDER_MALE)
    }

    @Test
    fun `onInputGenderFemaleChecked if call return female sign`() {
        every { resources.getString(R.string.gender_female) } returns STR_TEST_GENDER_FEMALE
        presenter.onInputGenderFemaleChecked()
        val genderFemale = presenter.traineeTmp?.gender
        Truth.assertThat(genderFemale).isEqualTo(STR_TEST_GENDER_FEMALE)
    }

    @Test
    fun `onInputAlphaAccountChecked if argument true return true`() {
        presenter.onInputAlphaAccountChecked(true)
        val hasAlphaAccount = presenter.traineeTmp?.hasAlphaAccount
        Truth.assertThat(hasAlphaAccount).isEqualTo(true)
    }

    @Test
    fun `onInputAlphaAccountChecked if argument false return false`() {
        presenter.onInputAlphaAccountChecked(false)
        val hasAlphaAccount = presenter.traineeTmp?.hasAlphaAccount
        Truth.assertThat(hasAlphaAccount).isEqualTo(false)
    }

    @Test
    fun `onInputSigmaAccountChecked if argument true return true`() {
        presenter.onInputSigmaAccountChecked(true)
        val hasSigmaAccount = presenter.traineeTmp?.hasSigmaAccount
        Truth.assertThat(hasSigmaAccount).isEqualTo(true)
    }

    @Test
    fun `onInputSigmaAccountChecked if argument false return false`() {
        presenter.onInputSigmaAccountChecked(false)
        val hasSigmaAccount = presenter.traineeTmp?.hasSigmaAccount
        Truth.assertThat(hasSigmaAccount).isEqualTo(false)
    }

    @Test
    fun `onInputComputerChecked if argument true return true`() {
        presenter.onInputComputerChecked(true)
        val hasComputer = presenter.traineeTmp?.hasComputer
        Truth.assertThat(hasComputer).isEqualTo(true)
    }

    @Test
    fun `onInputComputerChecked if argument false return false`() {
        presenter.onInputComputerChecked(false)
        val hasComputer = presenter.traineeTmp?.hasComputer
        Truth.assertThat(hasComputer).isEqualTo(false)
    }

    @Test
    fun `isDataEnough if trainee has fullName and gender return true`() {
        val isDataEnough = presenter.isDataEnough()
        Truth.assertThat(isDataEnough).isEqualTo(true)
    }

    @Test
    fun `isDataEnough if trainee is null return false`() {
        presenter.traineeTmp = null
        val isDataEnough = presenter.isDataEnough()
        Truth.assertThat(isDataEnough).isEqualTo(false)
    }

    @Test
    fun `isDataEnough if trainee has only fullName return false`() {
        presenter.traineeTmp = null
        presenter.onInputNameChanged(STR_TEST_FULL_NAME, STR_TEST_FULL_NAME.length)
        val isDataEnough = presenter.isDataEnough()
        Truth.assertThat(isDataEnough).isEqualTo(false)
    }

    @Test
    fun `isDataEnough if trainee has only gender return false`() {
        presenter.traineeTmp = null
        presenter.onInputGenderMaleChecked()
        val isDataEnough = presenter.isDataEnough()
        Truth.assertThat(isDataEnough).isEqualTo(false)
    }

    @Test
    fun `setTraineeId if traineeList empty return first free id - 1`() {
        every { model.traineeList } returns mutableListOf()
        presenter.generateTraineeId()
        val id = presenter.traineeTmp?.id
        Truth.assertThat(id).isEqualTo(1)
    }

    @Test
    fun `setTraineeId if traineeList has 1 item return first free id - 2`() {
        every { model.traineeList } returns mutableListOf(TRAINEE)
        presenter.generateTraineeId()
        val id = presenter.traineeTmp?.id
        Truth.assertThat(id).isEqualTo(2)
    }

    @Test
    fun `onAddButtonClicked if call 4 methods will be called 1 time`() {
        presenter.onAddButtonClicked()
        verify(exactly = 1) {
            model.addNewTrainee(TRAINEE)
            model.sortTraineeList()
            view.notifyNewTraineeAdded()
            presenter.clearInput()
        }
    }

    @Test
    fun `clearInput if call 5 methods will be called 1 time`() {
        presenter.clearInput()
        verify(exactly = 1) {
            presenter.clearInputName()
            view.clearInputGender()
            view.clearInputAlphaAccount()
            view.clearInputSigmaAccount()
            view.clearInputComputer()
        }
    }

    @Test
    fun `clearInputName if call 2 methods will be called 1 time`() {
        presenter.clearInput()
        verify(exactly = 1) {
            presenter.onInputNameChanged("", 0)
        }
    }

    companion object {
        private const val STR_TEST_FULL_NAME = "Иванов Иван Иваныч"
        private const val STR_TEST_NO_VALID_FULL_NAME = "@<>:.,"
        private const val STR_TEST_GENDER_MALE = "\u2642"
        private const val STR_TEST_GENDER_FEMALE = "\u2640"
        private const val ILLEGAL_ARGUMENT_EXCEPTION =
            "Argument 's' is null in onInputNameChanged method"
        private val TRAINEE = Trainee(
            id = 1,
            fullName = STR_TEST_FULL_NAME,
            gender = STR_TEST_GENDER_MALE,
            hasAlphaAccount = true,
            hasSigmaAccount = true,
            hasComputer = true
        )
    }
}