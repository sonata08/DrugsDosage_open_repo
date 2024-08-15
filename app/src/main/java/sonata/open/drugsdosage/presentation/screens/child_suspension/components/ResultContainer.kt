package sonata.open.drugsdosage.presentation.screens.child_suspension.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.presentation.common.components.InfoRow
import sonata.open.drugsdosage.presentation.common.components.ResultRow
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme
import sonata.open.drugsdosage.presentation.utils.extensions.round

@Composable
fun ResultContainer(
    singleDose: Double,
    medicine: Medicine,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ResultRow(
            text = stringResource(R.string.single_dose),
            result = singleDose.toString(),
            units = stringResource(R.string.ml)
        )
        InfoRow(
            text = stringResource(R.string.active_ingredient),
            units = medicine.activeIngredient
        )
        InfoRow(
            text = stringResource(R.string.doses_per_day),
            result = medicine.maxDosesPerDay.toString(),
            units = stringResource(R.string.times),
        )
        InfoRow(
            text = stringResource(R.string.min_interval_between_doses),
            result = medicine.minDosingInterval.toString(),
            units = stringResource(R.string.hours),
        )
        InfoRow(
            text = stringResource(R.string.max_doses_per_day),
            result = (medicine.maxDosesPerDay * singleDose).round().toString(),
            units = stringResource(R.string.ml),
        )
        Text(
            text =  medicine.info,
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.margin_medium))
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ResultContainerPreview() {
    val medicine = Medicine(
        name = "Paracetamol",
        activeIngredient = "Paracetamol",
        medicineAmountMl = 5.0,
        activeIngredientAmountMg = 120,
        maxDailyDose = 60,
        maxDosesPerDay = 4,
        minDosingInterval = 4,
        info = "Caution must be exerted when the weight-based dosing is applied to children weighing over 65 kg (143 lbs) so that daily adult paracetamol dose (4 g/day) is not exceeded."
    )
    DrugsDosageTheme {
        Surface {
            ResultContainer(singleDose = 10.0, medicine = medicine)
        }
    }
}

