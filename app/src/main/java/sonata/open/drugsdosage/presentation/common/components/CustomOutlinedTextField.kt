package sonata.open.drugsdosage.presentation.common.components


import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun CustomOutlinedTextField(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isValidNumber: Boolean,
    imeAction: ImeAction = ImeAction.Default,
    onDoneAction: () -> Unit = {}
) {
    var doseValue by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = doseValue,
        onValueChange = {
            // prevents invalid input, allows only Double or empty field
            doseValue = when (it.toDoubleOrNull()) {
                null -> if (it.isEmpty()) it else doseValue
                else -> it
            }
            onValueChange(doseValue)
        },
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        supportingText = {
            if (!isValidNumber) {
                Text(text = stringResource(R.string.text_field_error))
            }
        },
        isError = !isValidNumber,
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onDoneAction()
            }),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomOutlinedTextFieldPreview() {
    DrugsDosageTheme {
        Surface {
            CustomOutlinedTextField(
                isValidNumber = true,
                onValueChange = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CustomOutlinedTextFieldPreviewError() {
    DrugsDosageTheme {
        Surface {
            CustomOutlinedTextField(
                isValidNumber = false,
                onValueChange = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}