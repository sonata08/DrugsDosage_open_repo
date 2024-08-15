package sonata.open.drugsdosage.presentation.screens.suspension

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.common.components.CustomCard
import sonata.open.drugsdosage.presentation.common.components.InfoRow
import sonata.open.drugsdosage.presentation.common.components.MainButton
import sonata.open.drugsdosage.presentation.common.components.MaxDoseBlock
import sonata.open.drugsdosage.presentation.common.components.ResultRow
import sonata.open.drugsdosage.presentation.common.components.TextFieldWithDescription
import sonata.open.drugsdosage.presentation.screens.suspension.components.ActiveIngredientSuspBlock
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun SuspensionScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: SuspensionViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SuspensionScreen(
        updateActiveIngredient = { viewModel.updateActiveIngredient(it) },
        updateSuspQuantity = { viewModel.updateMedicineAmount(it) },
        updateWeight = { viewModel.updateWeight(it) },
        updateDailyOrSingle = { viewModel.updateDailyOrSingle(it) },
        updateDosePerKg = { viewModel.updateDosePerKg(it) },
        updateDosesPerDay = { viewModel.updateDosesPerDay(it) },
        updateDays = { viewModel.updateTreatmentDays(it) },
        calculateResult = { viewModel.calculateResult() },
        state = state,
        modifier = modifier
    )
}

@Composable
fun SuspensionScreen(
    updateActiveIngredient: (String) -> Unit,
    updateSuspQuantity: (String) -> Unit,
    updateWeight: (String) -> Unit,
    updateDailyOrSingle: (Int) -> Unit,
    updateDosePerKg: (String) -> Unit,
    updateDosesPerDay: (String) -> Unit,
    updateDays: (String) -> Unit,
    calculateResult: () -> Unit,
    state: SuspensionState,
    modifier: Modifier = Modifier
) {
    // check textFields validity only after button is clicked
    var isButtonClicked by remember { mutableStateOf(false) }

    Column(
        modifier
            .padding(dimensionResource(R.dimen.screen_margin))
            .verticalScroll(rememberScrollState()),
    ) {
        CustomCard {
            ActiveIngredientSuspBlock(
                onActiveIngredientChange = { updateActiveIngredient(it) },
                onSuspChange = { updateSuspQuantity(it) },
                isValidActiveIngredient = if (isButtonClicked) state.activeIngredientAmountMg > 0 else true,
                isValidSuspQuantity = if (isButtonClicked) state.medicineAmountMl > 0 else true,
            )
        }
        CustomCard {
            TextFieldWithDescription(
                description = stringResource(R.string.susp_set_child_weight),
                units = stringResource(R.string.kg),
                onDoseChange = { updateWeight(it) },
                imeAction = ImeAction.Next,
                isValidNumber = if (isButtonClicked) state.weight > 0 else true,
            )
        }
        CustomCard {
            MaxDoseBlock(
                onDoseChange = { updateDosePerKg(it) },
                onSelectionChange = { updateDailyOrSingle(it) },
                isValidNumber = if (isButtonClicked) state.dosePerKg > 0 else true,
            )
        }
        CustomCard {
            TextFieldWithDescription(
                description = stringResource(R.string.doses_per_day),
                units = stringResource(R.string.times),
                onDoseChange = { updateDosesPerDay(it) },
                isValidNumber = if (isButtonClicked) state.dosesPerDay > 0 else true,
            )
        }
        CustomCard {
            TextFieldWithDescription(
                description = stringResource(R.string.susp_treatment_duration),
                units = stringResource(R.string.days),
                onDoseChange = { updateDays(it) },
            )
        }
        MainButton(
            label = stringResource(R.string.calculate),
            onClick = {
                calculateResult()
                isButtonClicked = true
            },
        )
        ResultRow(
            text = stringResource(R.string.single_dose),
            result = state.singleDoseMl.toString(),
            units = stringResource(R.string.ml)
        )
        InfoRow(
            text = stringResource(R.string.susp_total_medicine_amount),
            result = state.totalMedicineAmountMl.toString(),
            units = stringResource(R.string.ml)
        )
        Spacer(modifier = Modifier.padding(bottom = dimensionResource(R.dimen.margin_medium)))
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun SuspensionScreenPreview() {
    DrugsDosageTheme {
        Surface {
            SuspensionScreenWithViewModel()
        }
    }
}