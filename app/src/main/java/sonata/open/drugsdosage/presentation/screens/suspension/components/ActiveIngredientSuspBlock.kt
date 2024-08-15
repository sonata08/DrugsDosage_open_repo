package sonata.open.drugsdosage.presentation.screens.suspension.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.common.components.CustomOutlinedTextField
import sonata.open.drugsdosage.presentation.common.components.FieldDescriptionText
import sonata.open.drugsdosage.presentation.common.components.UnitText
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun ActiveIngredientSuspBlock(
    onActiveIngredientChange: (String) -> Unit,
    onSuspChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isValidActiveIngredient: Boolean,
    isValidSuspQuantity: Boolean,
) {
    Column(modifier = modifier) {
        FieldDescriptionText(
            text = stringResource(R.string.susp_active_ingredient_in_medicine),
            )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//                .padding(top = dimensionResource(R.dimen.margin_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedTextField(
                onValueChange = { onActiveIngredientChange(it) },
                isValidNumber = isValidActiveIngredient,
                imeAction = ImeAction.Next,
                modifier = Modifier.weight(1f)
            )
            UnitText(
                text = "${stringResource(R.string.mg)}  /",
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.margin_small)),
            )
            CustomOutlinedTextField(
                onValueChange = { onSuspChange(it) },
                isValidNumber = isValidSuspQuantity,
                imeAction = ImeAction.Next,
                modifier = Modifier.weight(1f)
            )
            UnitText(
                text = stringResource(R.string.ml),
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.margin_small)),
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActiveIngredientSuspBlockPreview() {
    DrugsDosageTheme {
        Surface {
            ActiveIngredientSuspBlock(
                onActiveIngredientChange = {},
                onSuspChange = {},
                isValidActiveIngredient = true,
                isValidSuspQuantity = true,
            )
        }

    }
}