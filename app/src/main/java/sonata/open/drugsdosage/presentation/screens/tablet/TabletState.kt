package sonata.open.drugsdosage.presentation.screens.tablet

data class TabletState(
    val doseByDoctor: Double = 0.0,
    val doseAvailable: Double = 0.0,
    val unitDoctor: String = "",
    val unitAvailable: String = "",
    val singleDose: Double = 0.0,
)