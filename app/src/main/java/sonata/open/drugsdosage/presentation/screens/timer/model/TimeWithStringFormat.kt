package sonata.open.drugsdosage.presentation.screens.timer.model

import sonata.open.drugsdosage.Constants.DATE_FORMAT
import sonata.open.drugsdosage.data.database.Time
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class TimeWithStringFormat(
    val id: Int,
    val timeMillis: Long,
    val timeString: String = "",
) {
    constructor(time: Time) : this (
        id = time.id,
        timeMillis = time.time,
        timeString = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date(time.time)),
    )
}
