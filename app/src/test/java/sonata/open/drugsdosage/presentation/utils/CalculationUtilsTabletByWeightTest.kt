package sonata.open.drugsdosage.presentation.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import sonata.open.drugsdosage.presentation.screens.tablet_weight.TabletByWeightState
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateTabletByWeightSingleDose
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateTabletsQuantity
import sonata.open.drugsdosage.presentation.utils.extensions.round
import sonata.open.drugsdosage.presentation.common.DailyOrSingle


class CalculationUtilsTabletByWeightTest {

    @Test
    fun `calculateTabletByWeightSingleDose returns correct dose for SINGLE dose regimen`() {
        val state = TabletByWeightState(
            activeIngredientAmountMg = 200.0,
            weight = 10.0,
            dosePerKg = 2.0,
            dailyOrSingle = DailyOrSingle.SINGLE.ordinal,
            dosesPerDay = 3.0
        )

        val result = calculateTabletByWeightSingleDose(state)
        val expectedResult = (2.0 * 10.0).round()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `calculateTabletByWeightSingleDose returns correct dose for DAILY dose regimen`() {
        val state = TabletByWeightState(
            activeIngredientAmountMg = 200.0,
            weight = 10.0,
            dosePerKg = 2.0,
            dailyOrSingle = DailyOrSingle.DAILY.ordinal,
            dosesPerDay = 3.0
        )

        val result = calculateTabletByWeightSingleDose(state)
        val expectedResult = (2.0 * 10.0 / 3.0).round()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `calculateTabletsQuantity returns correct tablets quantity`() {
        val singleDose = 20.0
        val activeIngredientAmountMg = 200.0

        val result = calculateTabletsQuantity(singleDose, activeIngredientAmountMg)
        val expectedResult = (20.0 / 200.0).round()
        assertThat(result).isEqualTo(expectedResult)
    }
}