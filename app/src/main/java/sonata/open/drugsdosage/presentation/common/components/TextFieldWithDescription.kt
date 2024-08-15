package sonata.open.drugsdosage.presentation.common.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun TextFieldWithDescription(
    description: String,
    units: String,
    onDoseChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isValidNumber: Boolean = true,
    imeAction: ImeAction = ImeAction.Default,
) {
    Column(modifier) { // .padding(bottom = dimensionResource(R.dimen.margin_small))
        FieldDescriptionText(
            text = description,
        )
        TextFieldWithTextRow(
            units = units,
            onDoseChange = onDoseChange,
            isValidNumber = isValidNumber,
            imeAction = imeAction,
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextFieldWithDescriptionPreview() {
    DrugsDosageTheme {
        Surface {
            TextFieldWithDescription(
                description = stringResource(R.string.tabl_active_ingredient),
                units = stringResource(R.string.mg),
                onDoseChange = {},
            )
        }
    }
}