package sonata.open.drugsdosage.presentation.screens.tablet_weight

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.common.components.CustomCard
import sonata.open.drugsdosage.presentation.common.components.MainButton
import sonata.open.drugsdosage.presentation.common.components.MaxDoseBlock
import sonata.open.drugsdosage.presentation.common.components.ResultRow
import sonata.open.drugsdosage.presentation.common.components.TextFieldWithDescription
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun TabletWeightScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: TabletByWeightViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TabletWeightScreen(
        updateActiveIngredient = { viewModel.updateActiveIngredient(it) },
        updateWeight = { viewModel.updateWeight(it) },
        updateDailyOrSingle = { viewModel.updateDailyOrSingle(it) },
        updateDosePerKg = { viewModel.updateDosePerKg(it) },
        updateDosesPerDay = { viewModel.updateDosesPerDay(it) },
        calculateResult = { viewModel.calculateResult() },
        state = state,
        modifier = modifier
    )
}


@Composable
fun TabletWeightScreen(
    updateActiveIngredient: (String) -> Unit,
    updateWeight: (String) -> Unit,
    updateDailyOrSingle: (Int) -> Unit,
    updateDosePerKg: (String) -> Unit,
    updateDosesPerDay: (String) -> Unit,
    calculateResult: () -> Unit,
    state: TabletByWeightState,
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
            TextFieldWithDescription(
                description = stringResource(R.string.tabl_active_ingredient),
                units = stringResource(R.string.mg),
                onDoseChange = { updateActiveIngredient(it) },
                imeAction = ImeAction.Next,
                isValidNumber = if (isButtonClicked) state.activeIngredientAmountMg > 0 else true,
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

        MainButton(
            label = stringResource(R.string.calculate),
            onClick = {
                calculateResult()
                isButtonClicked = true
            },
        )
        ResultRow(
            text = stringResource(R.string.single_dose),
            result = state.singleDoseMg.toString(),
            units = stringResource(R.string.mg)
        )
        ResultRow(
            text = stringResource(R.string.or),
            result = state.tabletsQuantity.toString(),
            units = stringResource(R.string.tablets)
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = false)
@Composable
private fun TabletWeightScreenPreview() {
    DrugsDosageTheme {
        Surface {
            TabletWeightScreen(
                updateActiveIngredient = {},
                updateWeight = {},
                updateDailyOrSingle = {},
                updateDosePerKg = {},
                updateDosesPerDay = {},
                calculateResult = {},
                state = TabletByWeightState()
            )
        }
    }
}

