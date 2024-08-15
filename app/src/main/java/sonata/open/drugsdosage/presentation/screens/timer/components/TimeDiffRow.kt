package sonata.open.drugsdosage.presentation.screens.timer.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.common.LocalFirebaseAnalytics
import sonata.open.drugsdosage.presentation.screens.timer.TimeDiffState
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun TimeDiffRow(
    timeDiffState: TimeDiffState,
    modifier: Modifier = Modifier
) {
    val analytics = LocalFirebaseAnalytics.current
    val text = if (timeDiffState.minutes == Int.MAX_VALUE) {
        analytics.logEvent("time_error")
        R.string.error
    } else {
        if (timeDiffState.days > 0) {
            R.string.time_diff_days_hours_min
        } else {
            R.string.time_diff_hours_min
        }
    }
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
    ) {
        Text(
            text = stringResource(
                id = text,
                timeDiffState.hours,
                timeDiffState.minutes,
                timeDiffState.days
            ),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.margin_small))
                .fillMaxWidth()
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "en")
@Composable
private fun TimeDiffPreview() {
    DrugsDosageTheme {
        TimeDiffRow(timeDiffState = TimeDiffState(minutes = 3, hours = 6, days = 10))
    }
}