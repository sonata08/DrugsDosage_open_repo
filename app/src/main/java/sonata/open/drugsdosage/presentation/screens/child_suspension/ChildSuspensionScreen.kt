package sonata.open.drugsdosage.presentation.screens.child_suspension


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sonata.open.drugsdosage.Constants.LANGUAGE_EN
import sonata.open.drugsdosage.Constants.LANGUAGE_RU
import sonata.open.drugsdosage.Constants.LANGUAGE_UK
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.presentation.common.components.CustomCard
import sonata.open.drugsdosage.presentation.common.components.FieldDescriptionText
import sonata.open.drugsdosage.presentation.common.components.MainButton
import sonata.open.drugsdosage.presentation.common.components.TextFieldWithTextRow
import sonata.open.drugsdosage.presentation.screens.child_suspension.components.MedicineDropdownList
import sonata.open.drugsdosage.presentation.screens.child_suspension.components.ResultContainer
import sonata.open.drugsdosage.presentation.screens.tablet.components.TextFieldWithDropdown
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme
import java.util.Locale


@Composable
fun ChildSuspensionScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: ChildViewModel = hiltViewModel()
) {
    val childState by viewModel.childState.collectAsStateWithLifecycle()
    val language = Locale.getDefault().language
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = language) {
        viewModel.getMedicineList(language)
    }

    // Show progress bar while data is loading
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }

    val medicineListState by viewModel.medicineListState.collectAsStateWithLifecycle()

    ChildSuspensionScreen(
        language = language,
        medicineList = medicineListState,
        updateMedicine = { viewModel.updateMedicine(it) },
        updateWeight = { viewModel.updateWeight(it) },
        updateWeightUnits = { viewModel.updateWeightUnits(it) },
        calculateSingleDose = { viewModel.calculateSingleDose() },
        childState = childState,
        modifier = modifier
    )
}

@Composable
fun ChildSuspensionScreen(
    language: String,
    medicineList: List<Medicine>,
    updateMedicine: (Medicine) -> Unit,
    updateWeight: (String) -> Unit,
    updateWeightUnits: (String) -> Unit,
    calculateSingleDose: () -> Unit,
    childState: ChildState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.screen_margin))
            .verticalScroll(rememberScrollState())
    ) {
        CustomCard {
            FieldDescriptionText(
                text = stringResource(R.string.chooseMedicine),
                modifier = Modifier
            )
            MedicineDropdownList(
                items = medicineList,
                onValueChange = { updateMedicine(it) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        CustomCard {
            FieldDescriptionText(
                text = stringResource(R.string.child_weight),
            )
            if (language == LANGUAGE_UK || language == LANGUAGE_RU) {
                TextFieldWithTextRow(
                    units = stringResource(R.string.kg),
                    onDoseChange = { updateWeight(it) },
                    isValidNumber = childState.isValidWeight,
                    onDoneAction = { calculateSingleDose() }
                )
            } else {
                TextFieldWithDropdown(
                    items = stringArrayResource(id = R.array.unitsKgLbs).toList(),
                    onDoseChange = { updateWeight(it) },
                    onUnitChange = { updateWeightUnits(it) },
                    isValidNumber = childState.isValidWeight,
                    onDoneAction = { calculateSingleDose() }
                )
            }
        }
        MainButton(
            label = stringResource(R.string.calculate),
            onClick = { calculateSingleDose() },
        )
        ResultContainer(
            singleDose = childState.singleDose,
            medicine = childState.medicine
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ChildSuspensionScreenPreview() {
    DrugsDosageTheme {
        ChildSuspensionScreen(
            language = LANGUAGE_EN,
            medicineList = listOf(
                Medicine(
                    name = "Парацетамол",
                    activeIngredient = "Парацетамол",
                    medicineAmountMl = 5.0,
                    activeIngredientAmountMg = 120,
                    maxDailyDose = 60,
                    maxDosesPerDay = 4,
                    minDosingInterval = 4,
                    info = "Препарат протипоказаний для застосування дітям віком до 6 місяців. Дана лікарська форма застосовується дітям віком від 6 місяців до 12 років."
                )
            ),
            updateMedicine = {},
            updateWeight = {},
            updateWeightUnits = {},
            calculateSingleDose = {},
            childState = ChildState(
                Medicine(
                    name = "Парацетамол",
                    activeIngredient = "Парацетамол",
                    medicineAmountMl = 5.0,
                    activeIngredientAmountMg = 120,
                    maxDailyDose = 60,
                    maxDosesPerDay = 4,
                    minDosingInterval = 4,
                    info = "Препарат протипоказаний для застосування дітям віком до 6 місяців. Дана лікарська форма застосовується дітям віком від 6 місяців до 12 років."
                )
            ),
        )
    }
}

