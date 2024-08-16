package sonata.open.drugsdosage.presentation.screens.suspension

import sonata.open.drugsdosage.presentation.common.DailyOrSingle

data class SuspensionState(
    val activeIngredientAmountMg: Double = 0.0,
    val medicineAmountMl: Double = 0.0,
    val weight: Double = 0.0,
    val dosePerKg: Double = 0.0,
    val dailyOrSingle: Int = DailyOrSingle.DAILY.ordinal,
    val dosesPerDay: Double = 0.0,
    val treatmentDays: Double = 0.0,

    val singleDoseMl: Double = 0.0,
    val totalMedicineAmountMl: Double = 0.0,
)