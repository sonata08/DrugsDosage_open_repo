package sonata.open.drugsdosage.presentation.screens.tablet.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.presentation.common.components.CustomOutlinedTextField
import sonata.open.drugsdosage.presentation.common.components.DropdownList
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun TextFieldWithDropdown(
    items: List<String>,
    onDoseChange: (String) -> Unit,
    onUnitChange: (String) -> Unit,
    isValidNumber: Boolean,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Default,
    onDoneAction: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomOutlinedTextField(
            onValueChange = onDoseChange,
            modifier = modifier.weight(2f),
            isValidNumber = isValidNumber,
            imeAction = imeAction,
            onDoneAction = onDoneAction
        )
        DropdownList(
            items = items,
            onValueChange = onUnitChange,
            modifier = Modifier
                .weight(1f)
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DropdownRowPreview() {
    DrugsDosageTheme {
        TextFieldWithDropdown(
            items = listOf("mg", "g"),
            onDoseChange = {},
            onUnitChange = {},
            isValidNumber = true
        )
    }
}
