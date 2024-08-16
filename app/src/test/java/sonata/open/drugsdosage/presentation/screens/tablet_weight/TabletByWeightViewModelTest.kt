package sonata.open.drugsdosage.presentation.screens.tablet_weight

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import sonata.open.drugsdosage.presentation.common.DailyOrSingle
import sonata.open.drugsdosage.presentation.screens.tablet_weight.TabletByWeightViewModel


class TabletByWeightViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TabletByWeightViewModel

    @Before
    fun setup() {
        viewModel = TabletByWeightViewModel()
    }

    @Test
    fun `updateActiveIngredient updates state correctly and resets result`() = runTest {
        viewModel.updateActiveIngredient("200")
        val state = viewModel.state.first()
        assertThat(state.activeIngredientAmountMg).isEqualTo(200.0)
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }

    @Test
    fun `updateWeight updates state correctly and resets result`() = runTest {
        viewModel.updateWeight("70")
        val state = viewModel.state.first()
        assertThat(state.weight).isEqualTo(70.0)
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }

    @Test
    fun `updateDailyOrSingle updates state correctly and resets result`() = runTest {
        viewModel.updateDailyOrSingle(DailyOrSingle.SINGLE.ordinal)
        val state = viewModel.state.first()
        assertThat(state.dailyOrSingle).isEqualTo(DailyOrSingle.SINGLE.ordinal)
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }

    @Test
    fun `updateDosePerKg updates state correctly and resets result`() = runTest {
        viewModel.updateDosePerKg("1.5")
        val state = viewModel.state.first()
        assertThat(state.dosePerKg).isEqualTo(1.5)
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }

    @Test
    fun `updateDosesPerDay updates state correctly and resets result`() = runTest {
        viewModel.updateDosesPerDay("3.0")
        val state = viewModel.state.first()
        assertThat(state.dosesPerDay).isEqualTo(3.0)
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult updates state correctly for valid inputs and DAILY dose`() = runTest {
        viewModel.updateActiveIngredient("200")
        viewModel.updateWeight("51")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("4")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)

        viewModel.calculateResult()

        val state = viewModel.state.first()
        assertThat(state.singleDoseMg).isEqualTo(382.5)
        assertThat(state.tabletsQuantity).isEqualTo(1.9)
    }

    @Test
    fun `calculateResult updates state correctly for valid inputs and SINGLE dose`() = runTest {
        viewModel.updateActiveIngredient("200")
        viewModel.updateWeight("51")
        viewModel.updateDosePerKg("10")
        viewModel.updateDosesPerDay("20")
        viewModel.updateDailyOrSingle(DailyOrSingle.SINGLE.ordinal)

        viewModel.calculateResult()

        val state = viewModel.state.first()
        assertThat(state.singleDoseMg).isEqualTo(510.0)
        assertThat(state.tabletsQuantity).isEqualTo(2.5)
    }

    @Test
    fun `calculateResult doesn't calculate result with invalid ActiveIngredient`() = runTest {
        viewModel.updateActiveIngredient("")
        viewModel.updateWeight("70.0")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("3.0")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)

        viewModel.calculateResult()

        val state = viewModel.state.first()
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult doesn't calculate result with invalid Weight`() = runTest {
        viewModel.updateActiveIngredient("200")
        viewModel.updateWeight("")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("3.0")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)

        viewModel.calculateResult()

        val state = viewModel.state.first()
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult doesn't calculate result with invalid DosePerKg`() = runTest {
        viewModel.updateActiveIngredient("200")
        viewModel.updateWeight("51")
        viewModel.updateDosePerKg("")
        viewModel.updateDosesPerDay("3.0")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)

        viewModel.calculateResult()

        val state = viewModel.state.first()
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult doesn't calculate result with invalid DosesPerDay`() = runTest {
        viewModel.updateActiveIngredient("200")
        viewModel.updateWeight("51")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)

        viewModel.calculateResult()

        val state = viewModel.state.first()
        assertThat(state.singleDoseMg).isEqualTo(0.0)
        assertThat(state.tabletsQuantity).isEqualTo(0.0)
    }
}