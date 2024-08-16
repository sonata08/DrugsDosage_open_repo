package sonata.open.drugsdosage.presentation.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import sonata.open.drugsdosage.presentation.screens.suspension.SuspensionState
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateSuspensionSingleDose
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateSuspensionTotal
import sonata.open.drugsdosage.presentation.utils.extensions.round
import sonata.open.drugsdosage.presentation.common.DailyOrSingle


class CalculationUtilsSuspensionTest {

    @Test
    fun `state with valid inputs returns result`() {
        val state = SuspensionState(
            activeIngredientAmountMg = 200.0,
            medicineAmountMl = 5.0,
            weight = 23.0,
            dosePerKg = 30.0,
            dailyOrSingle = DailyOrSingle.DAILY.ordinal,
            dosesPerDay = 3.0,
        )

        val result = calculateSuspensionSingleDose(state)
        assertThat(result).isEqualTo(5.7)
    }

    @Test
    fun `calculateSuspensionSingleDose Ibuprofen returns correct dose for SINGLE dose regimen`() {
        val state = SuspensionState(
            activeIngredientAmountMg = 200.0,
            medicineAmountMl = 5.0,
            weight = 20.0,
            dosePerKg = 10.0,
            dailyOrSingle = DailyOrSingle.SINGLE.ordinal,
        )

        val result = calculateSuspensionSingleDose(state)
        assertThat(result).isEqualTo(5.0)
    }

    @Test
    fun `calculateSuspensionSingleDose Ibuprofen returns correct dose for DAILY dose regimen`() {
        val state = SuspensionState(
            activeIngredientAmountMg = 200.0,
            medicineAmountMl = 5.0,
            weight = 20.0,
            dosePerKg = 30.0,
            dailyOrSingle = DailyOrSingle.DAILY.ordinal,
            dosesPerDay = 3.0
        )

        val result = calculateSuspensionSingleDose(state)
        assertThat(result).isEqualTo(5.0)
    }

    @Test
    fun `calculateSuspensionSingleDose Paracetamol returns correct dose for SINGLE dose regimen`() {
        val state = SuspensionState(
            activeIngredientAmountMg = 160.0,
            medicineAmountMl = 5.0,
            weight = 20.0,
            dosePerKg = 15.0,
            dailyOrSingle = DailyOrSingle.SINGLE.ordinal,
        )

        val result = calculateSuspensionSingleDose(state)
        assertThat(result).isEqualTo(9.3)
    }

    @Test
    fun `calculateSuspensionSingleDose Paracetamol returns correct dose for DAILY dose regimen`() {
        val state = SuspensionState(
            activeIngredientAmountMg = 250.0,
            medicineAmountMl = 5.0,
            weight = 20.0,
            dosePerKg = 60.0,
            dailyOrSingle = DailyOrSingle.DAILY.ordinal,
            dosesPerDay = 4.0
        )

        val result = calculateSuspensionSingleDose(state)
        assertThat(result).isEqualTo(6.0)
    }

    @Test
    fun `calculateSuspensionTotal returns correct total dose`() {
        val singleDose = 5.0
        val dosesPerDay = 3.0
        val treatmentDays = 7.0

        val result = calculateSuspensionTotal(singleDose, dosesPerDay, treatmentDays)
        val expected = (5.0 * 3.0 * 7.0).round()
        assertThat(result).isEqualTo(expected)
    }
}