package com.example.sbertrainee.model

import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class ModelTest {

    private lateinit var model: Model

    @Before
    fun setUp() {
        model = Model()
    }

    @Test
    fun `addNewTrainee if Trainee add return list size equal 1`() {
        model.addNewTrainee(TRAINEE)
        val size = model.getTraineeList().size
        assertThat(size).isEqualTo(1)
    }

    @Test
    fun `getTraineeList if list has Trainee return Trainee`() {
        model.addNewTrainee(TRAINEE)
        val traineeList = model.getTraineeList()
        assertThat(traineeList[0]).isEqualTo(TRAINEE)
    }

    @Test
    fun `setSelectedItemPosition if position saved return this position`() {
        model.selectedItemPosition = 1
        val position = model.selectedItemPosition
        assertThat(position).isEqualTo(1)
    }

    @Test
    fun `getSelectedItemPosition if position not saved return 0`() {
        val position = model.selectedItemPosition
        assertThat(position).isEqualTo(0)
    }

    companion object {
        private const val STR_TEST_FULL_NAME = "Иванов Иван Иваныч"
        private const val STR_TEST_GENDER = "м"
        private const val BOOLEAN_TRUE = true
        private val TRAINEE = Trainee().apply {
            id = 1
            fullName = STR_TEST_FULL_NAME
            gender = STR_TEST_GENDER
            hasAlphaAccount = BOOLEAN_TRUE
            hasSigmaAccount = BOOLEAN_TRUE
            hasComputer = BOOLEAN_TRUE
        }
    }
}