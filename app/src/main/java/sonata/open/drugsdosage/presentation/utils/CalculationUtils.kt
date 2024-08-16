package sonata.open.drugsdosage.presentation.utils

import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.presentation.common.DailyOrSingle
import sonata.open.drugsdosage.presentation.screens.child_suspension.ChildState
import sonata.open.drugsdosage.presentation.screens.suspension.SuspensionState
import sonata.open.drugsdosage.presentation.screens.tablet_weight.TabletByWeightState
import sonata.open.drugsdosage.presentation.screens.timer.TimeDiffState
import sonata.open.drugsdosage.presentation.utils.UiConstants.LBS_TO_KG_FACTOR
import sonata.open.drugsdosage.presentation.utils.UiConstants.UNITS_LBS
import sonata.open.drugsdosage.presentation.utils.extensions.round
import java.util.Calendar

object CalculationUtils {

    // ChildViewModel
    fun calculateChildSuspension(medicine: Medicine, weight: Double): Double {
        val childMaxDailyDose = with(medicine) {
            maxDailyDose * weight / (activeIngredientAmountMg / medicineAmountMl)
        }
        return (childMaxDailyDose / medicine.maxDosesPerDay).round()
    }

    /**
     * Converts the child's weight from the currently selected units (if in pounds)
     * to kilograms and updates the internal state.
     */
    fun convertWeightToKg(childState: ChildState): Double {
        return if (childState.weightUnits == UNITS_LBS) {
            childState.weight * LBS_TO_KG_FACTOR
        } else {
            childState.weight
        }
    }

    // SuspensionViewModel
    fun calculateSuspensionSingleDose(state: SuspensionState): Double {
        with(state) {
            val singleDose =
                if (dailyOrSingle == DailyOrSingle.DAILY.ordinal) dosePerKg / dosesPerDay else dosePerKg
            val maxDose = singleDose * weight
            val dosePerMl = activeIngredientAmountMg / medicineAmountMl
            return (maxDose / dosePerMl).round()
        }
    }

    fun calculateSuspensionTotal(singleDose: Double, dosesPerDay: Double, treatmentDays: Double): Double {
        return (singleDose * dosesPerDay * treatmentDays).round()
    }

    // TabletByWeightViewModel
    fun calculateTabletByWeightSingleDose(state: TabletByWeightState): Double {
        with(state) {
            val singleDose = if (dailyOrSingle == DailyOrSingle.SINGLE.ordinal)
                (dosePerKg * weight).round() else (dosePerKg * weight / dosesPerDay).round()
            return singleDose
        }
    }

    fun calculateTabletsQuantity(singleDose: Double, activeIngredientAmountMg: Double): Double {
        return (singleDose / activeIngredientAmountMg).round()
    }

    // TimerViewModel
    fun calculateTimeDifference(lastDate: Long): TimeDiffState {
        val currentTime = Calendar.getInstance().time.time
        val diff = currentTime - lastDate

        val minutesTotal = diff / 1000 / 60
        val minutes = minutesTotal.toInt() % 60
        val hoursTotal = minutesTotal.toInt() / 60
        val hours = hoursTotal % 24
        val days = hoursTotal / 24
        return TimeDiffState(minutes = minutes, hours = hours, days = days)
    }
}