package sonata.open.drugsdosage.presentation.common.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun ResultRow(
    text: String,
    result: String,
    units: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.margin_medium)),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = text,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.margin_small)),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = result,
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.margin_small))
                .padding(horizontal = dimensionResource(R.dimen.margin_small)),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = units,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.margin_small)),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun ResultRowPreview() {
    DrugsDosageTheme {
        ResultRow(stringResource(R.string.single_dose), "2,5", stringResource(R.string.tablets))
    }
}