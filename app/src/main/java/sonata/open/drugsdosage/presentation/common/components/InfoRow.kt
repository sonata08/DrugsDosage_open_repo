package sonata.open.drugsdosage.presentation.common.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun InfoRow(
    text: String,
    units: String,
    modifier: Modifier = Modifier,
    result: String = "",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.margin_small)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        FieldDescriptionText(
            text = text,
            modifier = Modifier.weight(2f),

            )
        Text(
            text = result,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.margin_small)),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        FieldDescriptionText(
            text = units,
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InfoRowPreview() {
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
            InfoRow(
                text = stringResource(R.string.susp_total_medicine_amount),
                result = medicine.maxDosesPerDay.toString(),
                units = stringResource(R.string.times)
            )
        }
    }
}