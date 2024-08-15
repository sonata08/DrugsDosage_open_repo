package sonata.open.drugsdosage.presentation.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.presentation.utils.extensions.formatMedicineAmount

@Composable
fun Medicine.medicineToString(): String {
    val mg = stringResource(R.string.mg)
    val ml = stringResource(R.string.ml)
    return "$name $activeIngredientAmountMg $mg/${medicineAmountMl.formatMedicineAmount()} $ml"
}