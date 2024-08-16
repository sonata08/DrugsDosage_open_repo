package sonata.open.drugsdosage.presentation.screens.child_suspension

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import sonata.open.drugsdosage.Constants
import sonata.open.drugsdosage.MainCoroutineRule
import sonata.open.drugsdosage.MedicineList
import sonata.open.drugsdosage.data.repository.FakeMedicineRepository
import sonata.open.drugsdosage.presentation.utils.UiConstants.UNITS_LBS

@ExperimentalCoroutinesApi
class ChildViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ChildViewModel
    private lateinit var fakeRepository: FakeMedicineRepository

    @Before
    fun setUp() {
        fakeRepository = FakeMedicineRepository()
        viewModel = ChildViewModel(fakeRepository)
    }

    @Test
    fun `getMedicineList returns all items`() = runTest {
        viewModel.getMedicineList(Constants.LANGUAGE_UK)
        advanceUntilIdle()
        val list = viewModel.medicineListState.first()
        assertThat(list.size).isEqualTo(FakeMedicineRepository.medicineListUk.size)
    }

    @Test
    fun `getMedicineList sets isLoading to true and then false`() = runTest {
        viewModel.getMedicineList(Constants.LANGUAGE_UK)

        // isLoading should be true initially
        assertThat(viewModel.isLoading.value).isTrue()
        advanceUntilIdle()

        // isLoading should be false after fetching the data
        assertThat(viewModel.isLoading.value).isFalse()
    }

    @Test
    fun `getMedicineList does not call repository if language is the same`() = runTest {
        viewModel.getMedicineList(Constants.LANGUAGE_UK)
        advanceUntilIdle()

        // Call again with the same language
        viewModel.getMedicineList(Constants.LANGUAGE_UK)

        // Since the language is the same, isLoading should remain false
        assertThat(viewModel.isLoading.value).isFalse()
    }

    @Test
    fun `getMedicineList calls repository with new language`() = runTest {
        viewModel.getMedicineList(Constants.LANGUAGE_UK)
        advanceUntilIdle()

        // Call with a different language
        viewModel.getMedicineList(Constants.LANGUAGE_EN)
        advanceUntilIdle()

        // Verify that the repository's data is updated to the new language
        assertThat(FakeMedicineRepository.medicineListEn.size).isEqualTo(viewModel.medicineListState.value.size)
    }

    @Test
    fun `updateWeight updates the weight and resets single dose`() = runTest {
        viewModel.updateWeight("15.5")

        val state = viewModel.childState.first()
        assertThat(state.weight).isEqualTo(15.5)
        assertThat(state.singleDose).isEqualTo(0.0)
    }

    @Test
    fun `updateMedicine updates the medicine and resets single dose`() = runTest {
        val medicine = FakeMedicineRepository.medicineListUk[0]
        viewModel.updateMedicine(medicine)

        val state = viewModel.childState.first()
        assertThat(state.medicine).isEqualTo(medicine)
        assertThat(state.singleDose).isEqualTo(0.0)
    }

    @Test
    fun `updateWeightUnits updates the weight units and resets single dose`() = runTest {
        viewModel.updateWeightUnits(UNITS_LBS)

        val state = viewModel.childState.first()
        assertThat(state.weightUnits).isEqualTo(UNITS_LBS)
        assertThat(state.singleDose).isEqualTo(0.0)
    }

    @Test
    fun `calculateSingleDose calculates and updates single dose when weight is valid`() = runTest {
        val medicineWithResult = MedicineList.medicineList[0]
        viewModel.updateWeight(medicineWithResult.weight.toString())

        viewModel.updateMedicine(medicineWithResult.medicine)

        viewModel.calculateSingleDose()

        val state = viewModel.childState.first()
        assertThat(state.isValidWeight).isTrue()
        assertThat(state.singleDose).isEqualTo(medicineWithResult.expectedResult)
    }

    @Test
    fun `calculateSingleDose does not update single dose when weight is invalid`() = runTest {
        viewModel.updateWeight("0")

        viewModel.calculateSingleDose()

        val state = viewModel.childState.first()
        assertThat(state.isValidWeight).isFalse()
        assertThat(state.singleDose).isEqualTo(0.0)
    }
}