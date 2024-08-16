package sonata.open.drugsdosage.presentation.screens.timer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.screens.timer.model.TimeWithStringFormat

@Composable
fun ShowEmptyScreenText(
    list: List<TimeWithStringFormat>,
    modifier: Modifier = Modifier
) {
    if (list.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.margin_medium)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                stringResource(id = R.string.save_time),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,

                )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_medium)))
            Text(
                stringResource(id = R.string.press_button_save_time),
                textAlign = TextAlign.Center,
            )
        }
    }
}