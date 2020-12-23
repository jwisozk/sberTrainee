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
    fun `addNewTrainee if list has Trainee return Trainee`() {
        model.addNewTrainee(TRAINEE)
        val traineeList = model.traineeList
        assertThat(traineeList[0]).isEqualTo(TRAINEE)
    }

    @Test
    fun `addNewTrainee if traineeIdShow equals id added Trainee return true`() {
        model.addNewTrainee(TRAINEE)
        val traineeList = model.traineeList
        assertThat(traineeList[0].id).isEqualTo(model.traineeIdShow)
    }

    @Test
    fun `sortTraineeList if call return sorted items in ascending order`() {
        model.addNewTrainee(TRAINEE.copy(id = 2))
        model.addNewTrainee(TRAINEE.copy(id = 1))
        model.sortTraineeList()
        val traineeList = model.traineeList
        assertThat(traineeList[0].id).isLessThan(traineeList[1].id)
    }

    @Test
    fun `removeCurrentTrainee if call remove item equals selectedItemPosition`() {
        model.setSelectedItemPosition(0)
        model.addNewTrainee(TRAINEE)
        model.removeCurrentTrainee()
        val traineeList = model.traineeList
        assertThat(traineeList.isEmpty()).isEqualTo(true)
    }

    @Test
    fun `removeCurrentTrainee if traineeList is empty nothing to do`() {
        model.removeCurrentTrainee()
        val traineeList = model.traineeList
        assertThat(traineeList.isEmpty()).isEqualTo(true)
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