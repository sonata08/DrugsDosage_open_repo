package sonata.open.drugsdosage

import sonata.open.drugsdosage.data.model.Medicine

data class MedicineWithResult(
    val medicine: Medicine,
    val weight: Int,
    val expectedResult: Double,
    ) {
    override fun toString(): String {
        return "${medicine.activeIngredient} ${medicine.activeIngredientAmountMg}mg/" +
                "${medicine.medicineAmountMl}ml with weight ${weight}, result $expectedResult"
    }
}
