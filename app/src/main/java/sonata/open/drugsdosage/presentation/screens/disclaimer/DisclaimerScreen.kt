package sonata.open.drugsdosage.presentation.screens.disclaimer

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun DisclaimerScreen(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.disclaimer_text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .padding(dimensionResource(R.dimen.screen_margin))
            .verticalScroll(rememberScrollState())
        )
}


@Preview
@Composable
private fun DisclaimerPreview() {
    DrugsDosageTheme {
        Surface {
            DisclaimerScreen()
        }
    }
}