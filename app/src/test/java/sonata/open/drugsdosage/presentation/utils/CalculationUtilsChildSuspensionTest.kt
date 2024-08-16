package sonata.open.drugsdosage.presentation.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sonata.open.drugsdosage.MedicineList
import sonata.open.drugsdosage.MedicineWithResult
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateChildSuspension

@RunWith(Parameterized::class)
class CalculationUtilsChildSuspensionTest(
    private val medicineWithResult: MedicineWithResult
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = " {0}"
        )
        fun data():  Collection<MedicineWithResult>  {
            return MedicineList.medicineList
        }
    }

    @Test
    fun `calculate child suspension`() {
        val calculatedResult = calculateChildSuspension(
            medicineWithResult.medicine,
            medicineWithResult.weight.toDouble()
        )
        assertThat(calculatedResult).isEqualTo(medicineWithResult.expectedResult)
    }
}

