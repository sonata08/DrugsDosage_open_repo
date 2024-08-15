package sonata.open.drugsdosage.presentation.screens.child_suspension.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme
import sonata.open.drugsdosage.presentation.utils.extensions.formatMedicineAmount
import sonata.open.drugsdosage.presentation.utils.extensions.medicineToString


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDropdownList(
    items: List<Medicine>,
    onValueChange: (Medicine) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    val initialValue = if (items.isEmpty()) "" else items[0].medicineToString()

//    {
//        ""
//    } else {
//        "${items[0].name} " +
//                "${items[0].activeIngredientAmount} ${stringResource(R.string.mg)}/" +
//                "${items[0].medicineAmount.formatMedicineAmount()} ${stringResource(R.string.ml)}"
//    }


    var selectedMedicine by rememberSaveable(items) { mutableStateOf(initialValue) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedMedicine,
            onValueChange = { },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium,
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            items.forEach { medicine ->
                val medicineString = "${medicine.name} " +
                        "${medicine.activeIngredientAmountMg} ${stringResource(R.string.mg)}/" +
                        "${medicine.medicineAmountMl.formatMedicineAmount()} ${stringResource(R.string.ml)}"

                DropdownMenuItem(
                    text = {
                        Text(
                            text = medicineString,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                    onClick = {
                        onValueChange(medicine)
                        selectedMedicine = medicineString
                        isExpanded = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MedicineDropdownListPreview() {
    DrugsDosageTheme {
        MedicineDropdownList(testMedicineList(), {})
    }
}

private fun testMedicineList(): List<Medicine> {
    return listOf(
        Medicine(
            name = "Нурофен",
            activeIngredient = "Ібупрофен",
            medicineAmountMl = 5.0,
            activeIngredientAmountMg = 100,
            maxDailyDose = 30,
            maxDosesPerDay = 3,
            minDosingInterval = 6,
            info = ""
        ),
        Medicine(
            name = "Нурофен Форте",
            activeIngredient = "Ібупрофен",
            medicineAmountMl = 5.0,
            activeIngredientAmountMg = 200,
            maxDailyDose = 30,
            maxDosesPerDay = 3,
            minDosingInterval = 6,
            info = "Препарат застосовують дітям віком від 6 місяців, маса тіла яких не менше 8 кг, до 12 років."
        ),
        Medicine(
            name = "Парацетамол",
            activeIngredient = "Парацетамол",
            medicineAmountMl = 5.0,
            activeIngredientAmountMg = 120,
            maxDailyDose = 60,
            maxDosesPerDay = 4,
            minDosingInterval = 4,
            info = "Препарат протипоказаний для застосування дітям віком до 6 місяців. Дана лікарська форма застосовується дітям віком від 6 місяців до 12 років."
        ),
        Medicine(
            name = "Парацетамол",
            activeIngredient = "Парацетамол",
            medicineAmountMl = 5.0,
            activeIngredientAmountMg = 250,
            maxDailyDose = 60,
            maxDosesPerDay = 4,
            minDosingInterval = 4,
            info = "Препарат протипоказаний для застосування дітям віком до 6 місяців. Дана лікарська форма застосовується дітям віком від 6 місяців до 12 років."
        ),
    )
}