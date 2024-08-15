package sonata.open.drugsdosage.presentation.utils.extensions

import java.math.RoundingMode

fun Double.round(scale: Int = 1): Double {
    return this.toBigDecimal().setScale(scale, RoundingMode.DOWN).toDouble()
}

fun Double.formatMedicineAmount(): String {
    return if (this % 1 == 0.0) this.toInt().toString() else this.toString()
}