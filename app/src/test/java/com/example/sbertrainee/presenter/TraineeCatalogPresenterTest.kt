package com.example.sbertrainee.presenter

import android.view.View
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.model.Trainee
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class TraineeCatalogPresenterTest {

    @RelaxedMockK
    private lateinit var presenter: TraineeCatalogPresenter

    @RelaxedMockK
    private lateinit var model: Model

    @RelaxedMockK
    private lateinit var view: Contract.TraineeCatalogView

    @RelaxedMockK
    private lateinit var path: File

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = TraineeCatalogPresenter(view, model, path)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `refreshTraineeList if traineeList isn't empty 2 methods will be called 1 time`() {
        val traineeList = mutableListOf(TRAINEE)
        every { model.traineeList } returns traineeList
        presenter.refreshTraineeList()
        verify(exactly = 1) {
            presenter.updateTraineeList(traineeList)
            presenter.showLastAddedTrainee()
        }
    }

    @Test
    fun `refreshTraineeList if traineeList is empty 1 method will be called 1 time`() {
        val traineeList = mutableListOf<Trainee>()
        every { model.traineeList } returns traineeList
        presenter.refreshTraineeList()
        verify(exactly = 1) {
            view.setVisibilityFragmentView(View.INVISIBLE)
        }
    }

    @Test
    fun `onRemoveButtonClicked if call 1 method will be called 1 time`() {
        every { model.removeCurrentTrainee() } returns Unit
        presenter.refreshTraineeList()
        verify(exactly = 1) {
            view.setVisibilityFragmentView(View.INVISIBLE)
        }
    }

    @Test
    fun `updateTraineeList if call 1 method will be called 1 time`() {
        val traineeList = mutableListOf<Trainee>()
        presenter.updateTraineeList(traineeList)
        verify(exactly = 1) {
            view.updateTraineeList(traineeList)
        }
    }

    @Test
    fun `showLastAddedTrainee if call 1 method will be called 1 time`() {
        every { model.traineeIdShow } returns 0
        presenter.showLastAddedTrainee()
        verify(exactly = 1) {
            view.setSelectedItemPosition(model.traineeIdShow - 1)
        }
    }

    @Test
    fun `onItemPositionSelected if call 1 method will be called 1 time`() {
        val position = 0
        presenter.onItemPositionSelected(position)
        verify(exactly = 1) {
            model.selectedItemPosition = position
        }
    }

    companion object {
        private const val STR_TEST_FULL_NAME = "Иванов Иван Иваныч"
        private const val STR_TEST_GENDER_MALE = "\u2642"
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