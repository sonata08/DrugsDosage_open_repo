package sonata.open.drugsdosage.presentation.common.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun MaxDoseBlock(
    onDoseChange: (String) -> Unit,
    onSelectionChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isValidNumber: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
) {
    val optionsList = stringArrayResource(id = R.array.dose).toList()

    Column(
        modifier = modifier
    ) {
        FieldDescriptionText(
            text = stringResource(id = R.string.active_ingredient_max_dose),
        )
        SegmentedButton(
            optionsList = optionsList,
            onSelectionChange = onSelectionChange,
        )
        TextFieldWithTextRow(
            units = stringResource(id = R.string.mg_kg),
            onDoseChange = onDoseChange,
            isValidNumber = isValidNumber,
            imeAction = imeAction,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.margin_small)),
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MaxDoseBlockPreview() {
    DrugsDosageTheme {
        Surface {
            MaxDoseBlock(
                onDoseChange = {},
                onSelectionChange = {}
            )
        }
    }
}