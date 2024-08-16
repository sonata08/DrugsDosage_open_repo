package sonata.open.drugsdosage.presentation.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme


@Composable
fun MainButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onClick()
                focusManager.clearFocus()
            },
            shape = MaterialTheme.shapes.large,
            modifier = modifier
                .padding(vertical = dimensionResource(R.dimen.padding_button_top))
                .height(dimensionResource(R.dimen.button_height)),
            elevation = ButtonDefaults.buttonElevation(4.dp)

        ) {
            Text(text = label.uppercase())
        }
    }

}

@Preview
@Composable
private fun ButtonPreview() {
    DrugsDosageTheme {
        MainButton(
            label = stringResource(id = R.string.send_message),
            onClick = {}
        )
    }
}

