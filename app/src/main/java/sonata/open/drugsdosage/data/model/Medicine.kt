package sonata.open.drugsdosage.data.model


/**
 * Represents a medicine for ChildSuspensionScreen
 *
 * @property name Resource ID for the medicine name
 * @property activeIngredient Resource ID for the active ingredient
 * @property medicineAmountMl Amount of medicine
 * @property activeIngredientAmountMg the amount of active ingredient in [medicineAmountMl] (100 mg)
 * @property maxDailyDose maximum daily dose (in 24 hours) mg/kg
 * @property maxDosesPerDay max number of doses per day (4 times)
 * @property minDosingInterval  minimum interval between doses in hours (4 hours)
 * @property info Resource ID for additional information about the medicine
 */
data class Medicine(
    val name: String = "",
    val activeIngredient: String = "",
    val medicineAmountMl: Double = 0.0,
    val activeIngredientAmountMg: Int = 0,
    val maxDailyDose: Int = 0,
    val maxDosesPerDay: Int = 0,
    val minDosingInterval: Int = 0,
    val info: String = "",
)