package sonata.open.drugsdosage.presentation.screens.timer.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.data.database.Time
import sonata.open.drugsdosage.presentation.screens.timer.model.TimeWithStringFormat
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun TimeRow(
    time: TimeWithStringFormat,
    onDeleteTime: (Time) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = time.timeString,
                modifier = Modifier
                    .weight(2f)
                    .padding(start = dimensionResource(id = R.dimen.margin_small)),
                style = MaterialTheme.typography.bodySmall
            )
            IconButton(
                onClick = { onDeleteTime(Time(time.id, time.timeMillis)) },
            ) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.delete),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,  showBackground = true)
@Composable
private fun TimeRowPreview() {
    DrugsDosageTheme {
        TimeRow(
            time = TimeWithStringFormat(1, 0L, "time_1"),
            onDeleteTime = {}
        )
    }

}