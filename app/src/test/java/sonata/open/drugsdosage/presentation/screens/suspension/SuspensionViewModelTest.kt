package sonata.open.drugsdosage.presentation.screens.suspension

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import sonata.open.drugsdosage.MainCoroutineRule
import sonata.open.drugsdosage.presentation.common.DailyOrSingle

class SuspensionViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SuspensionViewModel

    @Before
    fun setUp() {
        viewModel = SuspensionViewModel()
    }

    @Test
    fun `updateActiveIngredient updates activeIngredient and resets single dose`() = runTest {
        viewModel.updateActiveIngredient("200")

        val state = viewModel.state.first()
        Truth.assertThat(state.activeIngredientAmountMg).isEqualTo(200.0)
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
    }

    @Test
    fun `updateMedicineAmount updates medicineAmount and resets single dose`() = runTest {
        viewModel.updateMedicineAmount("5")

        val state = viewModel.state.first()
        Truth.assertThat(state.medicineAmountMl).isEqualTo(5.0)
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
    }

    @Test
    fun `updateWeight updates the weight and resets single dose`() = runTest {
        viewModel.updateWeight("15.5")

        val state = viewModel.state.first()
        Truth.assertThat(state.weight).isEqualTo(15.5)
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
    }

    @Test
    fun `updateWeight with invalid input doesn't update the weight and resets single dose`() = runTest {
        viewModel.updateWeight("")

        val state = viewModel.state.first()
        Truth.assertThat(state.weight).isEqualTo(0.0)
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
    }

    @Test
    fun `updateDailyOrSingle updates DailyOrSingle with DAILY and resets single dose`() = runTest {
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)

        val state = viewModel.state.first()
        Truth.assertThat(state.dailyOrSingle).isEqualTo(DailyOrSingle.DAILY.ordinal)
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
    }

    @Test
    fun `updateDailyOrSingle updates DailyOrSingle with SINGLE and resets single dose`() = runTest {
        viewModel.updateDailyOrSingle(DailyOrSingle.SINGLE.ordinal)

        val state = viewModel.state.first()
        Truth.assertThat(state.dailyOrSingle).isEqualTo(DailyOrSingle.SINGLE.ordinal)
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
    }

    @Test
    fun `updateDosePerKg updates dosePerKg and resets single dose`() = runTest {
        viewModel.updateDosePerKg("20")

        val state = viewModel.state.first()
        Truth.assertThat(state.dosePerKg).isEqualTo(20.0)
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
    }

    @Test
    fun `updateDosesPerDay updates dosesPerDay and resets single dose`() = runTest {
        viewModel.updateDosesPerDay("3")

        val state = viewModel.state.first()
        Truth.assertThat(state.dosesPerDay).isEqualTo(3.0)
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
    }

    @Test
    fun `updateTreatmentDays updates dosesPerDay and resets totalMedicineAmountMl`() = runTest {
        viewModel.updateTreatmentDays("7")

        val state = viewModel.state.first()
        Truth.assertThat(state.treatmentDays).isEqualTo(7.0)
        Truth.assertThat(state.totalMedicineAmountMl).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult with valid treatmentDays updates singleDoseMl and totalMedicineAmountMl`() = runTest {
        viewModel.updateMedicineAmount("5")
        viewModel.updateActiveIngredient("200")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)
        viewModel.updateWeight("20")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("3")
        viewModel.updateTreatmentDays("5")

        viewModel.calculateResult()

        val state = viewModel.state.first()
        Truth.assertThat(state.singleDoseMl).isEqualTo(5.0)
        Truth.assertThat(state.totalMedicineAmountMl).isEqualTo(75.0)
    }

    @Test
    fun `calculateResult with invalid treatmentDays doesn't update totalMedicineAmountMl`() = runTest {
        viewModel.updateMedicineAmount("5")
        viewModel.updateActiveIngredient("200")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)
        viewModel.updateWeight("20")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("3")
        viewModel.updateTreatmentDays("")

        viewModel.calculateResult()

        val state = viewModel.state.first()
        Truth.assertThat(state.singleDoseMl).isEqualTo(5.0)
        Truth.assertThat(state.totalMedicineAmountMl).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult without treatmentDays updates only singleDoseMl`() = runTest {
        viewModel.updateMedicineAmount("5")
        viewModel.updateActiveIngredient("200")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)
        viewModel.updateWeight("20")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("3")

        viewModel.calculateResult()

        val state = viewModel.state.first()
        Truth.assertThat(state.singleDoseMl).isEqualTo(5.0)
        Truth.assertThat(state.totalMedicineAmountMl).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult with incorrect MedicineAmount doesn't update singleDose`() = runTest {
        viewModel.updateMedicineAmount("")
        viewModel.updateActiveIngredient("200")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)
        viewModel.updateWeight("20")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("3")

        viewModel.calculateResult()

        val state = viewModel.state.first()
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
        Truth.assertThat(state.totalMedicineAmountMl).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult with incorrect ActiveIngredient doesn't update singleDose`() = runTest {
        viewModel.updateMedicineAmount("5")
        viewModel.updateActiveIngredient("")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)
        viewModel.updateWeight("20")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("3")

        viewModel.calculateResult()

        val state = viewModel.state.first()
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
        Truth.assertThat(state.totalMedicineAmountMl).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult with incorrect DosePerKg doesn't update singleDose`() = runTest {
        viewModel.updateMedicineAmount("5")
        viewModel.updateActiveIngredient("200")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)
        viewModel.updateWeight("20")
        viewModel.updateDosePerKg("")
        viewModel.updateDosesPerDay("3")

        viewModel.calculateResult()

        val state = viewModel.state.first()
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
        Truth.assertThat(state.totalMedicineAmountMl).isEqualTo(0.0)
    }

    @Test
    fun `calculateResult with incorrect Weight doesn't update singleDose`() = runTest {
        viewModel.updateMedicineAmount("5")
        viewModel.updateActiveIngredient("200")
        viewModel.updateDailyOrSingle(DailyOrSingle.DAILY.ordinal)
        viewModel.updateWeight("")
        viewModel.updateDosePerKg("30")
        viewModel.updateDosesPerDay("3")

        viewModel.calculateResult()

        val state = viewModel.state.first()
        Truth.assertThat(state.singleDoseMl).isEqualTo(0.0)
        Truth.assertThat(state.totalMedicineAmountMl).isEqualTo(0.0)
    }
}