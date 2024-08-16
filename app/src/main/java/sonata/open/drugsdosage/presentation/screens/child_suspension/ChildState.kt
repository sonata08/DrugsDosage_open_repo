package sonata.open.drugsdosage.presentation.screens.child_suspension

import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.presentation.utils.UiConstants.UNITS_KG

data class ChildState(
    val medicine: Medicine,
    val weight: Double = 0.0,
    val singleDose: Double = 0.0,
    val weightUnits: String = UNITS_KG,
    val isValidWeight: Boolean = true,
)
