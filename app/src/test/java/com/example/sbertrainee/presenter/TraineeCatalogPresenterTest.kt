package com.example.sbertrainee.presenter

import androidx.recyclerview.widget.RecyclerView
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.presenter.adapter.TraineeAdapter
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Test

class TraineeCatalogPresenterTest {

    private lateinit var presenter: TraineeCatalogPresenter
    private lateinit var model: Model
    private lateinit var traineeAdapter: TraineeAdapter
    @RelaxedMockK
    private lateinit var view: Contract.TraineeCatalogView
    @RelaxedMockK
    private lateinit var recyclerView: RecyclerView

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        model = Model()
        traineeAdapter = TraineeAdapter(ArrayList())
        recyclerView.adapter = traineeAdapter
        presenter = TraineeCatalogPresenter(view, model)
    }

    @Test
    fun `onItemPositionSelected if call return position`() {
        presenter.onItemPositionSelected(1)
        val position = model.getSelectedItemPosition()
        assertThat(position).isEqualTo(1)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}