package sonata.open.drugsdosage.presentation.common.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun TextFieldWithTextRow(
    units: String,
    onDoseChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isValidNumber: Boolean = true,
    imeAction: ImeAction = ImeAction.Default,
    onDoneAction: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomOutlinedTextField(
            onValueChange = { onDoseChange(it) },
            isValidNumber = isValidNumber,
            imeAction = imeAction,
            onDoneAction = onDoneAction,
            modifier = Modifier
                .weight(1f)
//                .fillMaxWidth(0.5f)
        )
        UnitText(
            text = units,
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.margin_small))
                .weight(1f),
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextFieldWithTextRowPreview() {
    DrugsDosageTheme {
        Surface {
            TextFieldWithTextRow(
                units = "mg", onDoseChange = {}
            )
        }
    }
}