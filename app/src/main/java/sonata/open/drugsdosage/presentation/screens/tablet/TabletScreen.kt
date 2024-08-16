package sonata.open.drugsdosage.presentation.screens.tablet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.common.components.CustomCard
import sonata.open.drugsdosage.presentation.common.components.FieldDescriptionText
import sonata.open.drugsdosage.presentation.common.components.MainButton
import sonata.open.drugsdosage.presentation.common.components.ResultRow
import sonata.open.drugsdosage.presentation.screens.tablet.components.TextFieldWithDropdown
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun TabletScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: TabletViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TabletScreen(
        updateDoseDoctor = { viewModel.updateDoseDoctor(it) },
        updateDoseAvailable = { viewModel.updateDoseAvailable(it) },
        updateUnitDoctor = { viewModel.updateUnitDoctor(it) },
        updateUnitAvailable = { viewModel.updateUnitAvailable(it) },
        calculateSingleDose = { viewModel.calculateSingleDose() },
        state = state,
        modifier = modifier
    )
}


@Composable
fun TabletScreen(
    updateDoseDoctor: (String) -> Unit,
    updateDoseAvailable: (String) -> Unit,
    updateUnitDoctor: (String) -> Unit,
    updateUnitAvailable: (String) -> Unit,
    calculateSingleDose: () -> Unit,
    state: TabletState,
    modifier: Modifier = Modifier,
) {
    // check textFields validity only after button is clicked
    var isButtonClicked by remember { mutableStateOf(false) }
    val units = stringArrayResource(id = R.array.units).toList()

    Column(
        modifier
            .padding(dimensionResource(R.dimen.screen_margin))
            .verticalScroll(rememberScrollState()),
    ) {
        CustomCard {
            FieldDescriptionText(
                text = stringResource(R.string.tabl_dosage_doctor),
            )
            TextFieldWithDropdown(
                items = units,
                onDoseChange = { updateDoseDoctor(it) },
                onUnitChange = { updateUnitDoctor(it) },
                imeAction = ImeAction.Next,
                isValidNumber = if (isButtonClicked) state.doseByDoctor > 0 else true,
            )
        }
        CustomCard {
            FieldDescriptionText(
                text = stringResource(R.string.tabl_dosage_available),
            )
            TextFieldWithDropdown(
                items = units,
                onDoseChange = { updateDoseAvailable(it) },
                onUnitChange = { updateUnitAvailable(it) },
                isValidNumber = if (isButtonClicked) state.doseAvailable > 0 else true,
                onDoneAction = {
                    calculateSingleDose()
                    isButtonClicked = true
                }
            )
        }
        MainButton(
            label = stringResource(R.string.calculate),
            onClick = {
                calculateSingleDose()
                isButtonClicked = true
            },
        )
        ResultRow(
            text = stringResource(R.string.single_dose),
            result = state.singleDose.toString(),
            units = stringResource(R.string.tablets),
        )
    }
}


@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TabletScreenPreview() {
    DrugsDosageTheme {
        Surface {
            TabletScreen(
                updateDoseDoctor = { },
                updateDoseAvailable = { },
                updateUnitDoctor = { },
                updateUnitAvailable = { },
                calculateSingleDose = { },
                state = TabletState(),
            )
        }

    }
}
