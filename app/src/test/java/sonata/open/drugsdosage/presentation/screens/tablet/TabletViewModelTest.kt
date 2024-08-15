package sonata.open.drugsdosage.presentation.screens.tablet

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import sonata.open.drugsdosage.presentation.screens.tablet.TabletViewModel

class TabletViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TabletViewModel

    @Before
    fun setup() {
        viewModel = TabletViewModel()
    }

    @Test
    fun `updateDoseDoctor updates state correctly`() = runTest {
        viewModel.updateDoseDoctor("500.0")
        val state = viewModel.state.first()
        assertThat(state.doseByDoctor).isEqualTo(500.0)
    }

    @Test
    fun `updateDoseAvailable updates state correctly`() = runTest {
        viewModel.updateDoseAvailable("200.0")
        val state = viewModel.state.first()
        assertThat(state.doseAvailable).isEqualTo(200.0)
    }

    @Test
    fun `updateUnitDoctor updates state correctly`() = runTest {
        viewModel.updateUnitDoctor("mg")
        val state = viewModel.state.first()
        assertThat(state.unitDoctor).isEqualTo("mg")
    }

    @Test
    fun `updateUnitAvailable updates state correctly`() = runTest {
        viewModel.updateUnitAvailable("g")
        val state = viewModel.state.first()
        assertThat(state.unitAvailable).isEqualTo("g")
    }

    @Test
    fun `calculateSingleDose calculates correct dose when units are mg and mg`() = runTest {
        viewModel.updateDoseDoctor("500")
        viewModel.updateDoseAvailable("250")
        viewModel.updateUnitDoctor("mg")
        viewModel.updateUnitAvailable("mg")

        viewModel.calculateSingleDose()

        val state = viewModel.state.first()
        assertThat(state.singleDose).isEqualTo(2.0)
    }

    @Test
    fun `calculateSingleDose calculates correct dose when units are mg and g`() = runTest {
        viewModel.updateDoseDoctor("500")
        viewModel.updateDoseAvailable("0.5")
        viewModel.updateUnitDoctor("mg")
        viewModel.updateUnitAvailable("g")

        viewModel.calculateSingleDose()

        val state = viewModel.state.first()
        assertThat(state.singleDose).isEqualTo(1.0)
    }

    @Test
    fun `calculateSingleDose calculates correct dose when units are g and mg`() = runTest {
        viewModel.updateDoseDoctor("0.5")
        viewModel.updateDoseAvailable("500")
        viewModel.updateUnitDoctor("g")
        viewModel.updateUnitAvailable("mg")

        viewModel.calculateSingleDose()

        val state = viewModel.state.first()
        assertThat(state.singleDose).isEqualTo(1.0)
    }

    @Test
    fun `calculateSingleDose does not update singleDose if doseDoctor is invalid`() = runTest {
        viewModel.updateDoseDoctor("")
        viewModel.updateDoseAvailable("500")
        viewModel.updateUnitDoctor("mg")
        viewModel.updateUnitAvailable("mg")
        viewModel.calculateSingleDose()

        val state = viewModel.state.first()
        assertThat(state.singleDose).isEqualTo(0.0)
    }

    @Test
    fun `calculateSingleDose does not update singleDose if doseAvailable is invalid`() = runTest {
        viewModel.updateDoseDoctor("500")
        viewModel.updateDoseAvailable("")
        viewModel.updateUnitDoctor("mg")
        viewModel.updateUnitAvailable("mg")
        viewModel.calculateSingleDose()

        val state = viewModel.state.first()
        assertThat(state.singleDose).isEqualTo(0.0)
    }
}