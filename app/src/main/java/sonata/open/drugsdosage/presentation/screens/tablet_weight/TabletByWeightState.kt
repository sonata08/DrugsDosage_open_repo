package sonata.open.drugsdosage.presentation.screens.tablet_weight

import sonata.open.drugsdosage.presentation.common.DailyOrSingle


data class TabletByWeightState(
    val activeIngredientAmountMg: Double = 0.0,
    val weight: Double = 0.0,
    val dosePerKg: Double = 0.0,
    val dailyOrSingle: Int = DailyOrSingle.DAILY.ordinal,
    val dosesPerDay: Double = 0.0,
    val singleDoseMg: Double = 0.0,
    val tabletsQuantity: Double = 0.0,
)


