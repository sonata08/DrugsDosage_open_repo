package sonata.open.drugsdosage.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(bottom = dimensionResource(id = R.dimen.margin_small)),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
        ),
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_small))
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun CustomCardPreview() {
    DrugsDosageTheme {
        CustomCard {
            Text("SOME TEXT")
            Text("other text")
        }
    }
}