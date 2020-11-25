package com.example.sbertrainee.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Test

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import org.junit.Rule

class ModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var model: Model

    @Before
    fun prepare() {
        MockKAnnotations.init(this)
        model = Model()
    }

    @Test
    fun `setFullName if name saved return this name`() {
        model.setFullName(STR_TEST_FULL_NAME)
        val value = model.trainee?.fullName
        assertThat(value).isEqualTo(STR_TEST_FULL_NAME)
    }

    @Test
    fun `setGender if gender saved return this gender`() {
        model.setGender(STR_TEST_GENDER)
        val value = model.trainee?.gender
        assertThat(value).isEqualTo(STR_TEST_GENDER)
    }

    @Test
    fun `setHasAlphaAccount if value saved return true`() {
        model.setHasAlphaAccount(BOOLEAN_TRUE)
        val value = model.trainee?.hasAlphaAccount
        assertThat(value).isEqualTo(true)
    }

    @Test
    fun `setHasSigmaAccount if value saved return true`() {
        model.setHasSigmaAccount(BOOLEAN_TRUE)
        val value = model.trainee?.hasSigmaAccount
        assertThat(value).isEqualTo(true)
    }

    @Test
    fun `setHasComputer if value saved return true`() {
        model.setHasComputer(BOOLEAN_TRUE)
        val value = model.trainee?.hasComputer
        assertThat(value).isEqualTo(true)
    }

    @Test
    fun `getGenderRes if Id doesn't match radio's id return null`() {
        val value = model.getGenderRes(INT_RANDOM)
        assertThat(value).isEqualTo(null)
    }

    @Test
    fun `addTrainee if Trainee add return list size equal 1`() {
        model.addTrainee(TRAINEE)
        val value = model.traineeListLiveData.value?.size
        assertThat(value).isEqualTo(1)
    }

    @Test
    fun `newTrainee if create Trainee return counterId equal 1`() {
        model.trainee = TRAINEE
        val value = model.newTrainee()
        assertThat(value?.id).isEqualTo(1)
    }

    @Test
    fun `getTraineeFromList if list has Trainee return Trainee`() {
        model.addTrainee(TRAINEE)
        val value = model.getTraineeFromList(0)
        assertThat(value).isEqualTo(TRAINEE)
    }

    @Test
    fun `setIsAddedViewPagerFragment if value saved return true`() {
        model.setIsAddedViewPagerFragment(BOOLEAN_TRUE)
        val value = model.isAddedViewPagerFragmentLiveData.value
        assertThat(value).isEqualTo(true)
    }

    @Test
    fun `isDataEnough if trainee has fullName and gender return true`() {
        model.clear()
        model.trainee = TRAINEE
        val value = model.isDataEnough()
        assertThat(value).isEqualTo(true)
    }

    @Test
    fun `isDataEnough if trainee has only fullName return false`() {
        model.clear()
        model.trainee = Trainee(fullName = STR_TEST_FULL_NAME)
        val value = model.isDataEnough()
        assertThat(value).isEqualTo(false)
    }

    @Test
    fun `isDataEnough if trainee has only gender return false`() {
        model.clear()
        model.trainee = Trainee(gender = STR_TEST_GENDER)
        val value = model.isDataEnough()
        assertThat(value).isEqualTo(false)
    }

    @Test
    fun `clear if clear trainee return null`() {
        model.clear()
        val value = model.trainee
        assertThat(value).isEqualTo(null)
    }

    companion object {
        private const val STR_TEST_FULL_NAME = "Иванов Иван Иваныч"
        private const val STR_TEST_GENDER = "м"
        private const val BOOLEAN_TRUE = true
        private const val INT_RANDOM = 42
        private val TRAINEE = Trainee().apply {
            fullName = STR_TEST_FULL_NAME
            gender = STR_TEST_GENDER
            hasAlphaAccount = BOOLEAN_TRUE
            hasSigmaAccount = BOOLEAN_TRUE
            hasComputer = BOOLEAN_TRUE
        }
    }
}