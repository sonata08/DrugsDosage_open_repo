package sonata.open.drugsdosage.presentation.screens.suspension

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateSuspensionSingleDose
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateSuspensionTotal
import sonata.open.drugsdosage.presentation.common.DailyOrSingle
import javax.inject.Inject

@HiltViewModel
class SuspensionViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SuspensionState())
    val state = _state.asStateFlow()

    fun updateActiveIngredient(value: String) {
        _state.update { it.copy(activeIngredientAmountMg = value.toDoubleOrNull() ?: 0.0) }
        resetResult()
    }

    fun updateMedicineAmount(value: String) {
        _state.update { it.copy(medicineAmountMl = value.toDoubleOrNull() ?: 0.0) }
        resetResult()
    }

    fun updateWeight(value: String) {
        _state.update { it.copy(weight = value.toDoubleOrNull() ?: 0.0) }
        resetResult()
    }

    fun updateDailyOrSingle(value: Int) {
        _state.update { it.copy(dailyOrSingle = value) }
        resetResult()
    }

    fun updateDosePerKg(value: String) {
        _state.update { it.copy(dosePerKg = value.toDoubleOrNull() ?: 0.0) }
        resetResult()
    }

    fun updateDosesPerDay(value: String) {
        _state.update { it.copy(dosesPerDay = value.toDoubleOrNull() ?: 0.0) }
        resetResult()
    }

    fun updateTreatmentDays(value: String) {
        _state.update {
            it.copy(
                treatmentDays = value.toDoubleOrNull() ?: 0.0,
                totalMedicineAmountMl = 0.0
            )
        }
    }

    fun calculateResult() {
        if (!allInputsAreValid()) return
        val singleDose = calculateSuspensionSingleDose(_state.value)
        val totalMedicineAmount = calculateSuspensionTotal(
            singleDose = singleDose,
            dosesPerDay = _state.value.dosesPerDay,
            treatmentDays = _state.value.treatmentDays,
        )
        _state.update {
            it.copy(
                singleDoseMl = singleDose,
                totalMedicineAmountMl = totalMedicineAmount
            )
        }
    }

    private fun allInputsAreValid(): Boolean {
        with(_state.value) {
            return activeIngredientAmountMg > 0
                    && medicineAmountMl > 0
                    && weight > 0
                    && dosePerKg > 0
                    && if (dailyOrSingle == DailyOrSingle.DAILY.ordinal) dosesPerDay > 0 else true
        }
    }

    private fun resetResult() {
        _state.update { it.copy(singleDoseMl = 0.0, totalMedicineAmountMl = 0.0) }
    }
}
