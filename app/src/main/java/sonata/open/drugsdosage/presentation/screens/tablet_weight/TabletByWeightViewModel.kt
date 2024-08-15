package sonata.open.drugsdosage.presentation.screens.tablet_weight

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateTabletByWeightSingleDose
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateTabletsQuantity
import sonata.open.drugsdosage.presentation.common.DailyOrSingle
import javax.inject.Inject

@HiltViewModel
class TabletByWeightViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(TabletByWeightState())
    val state = _state.asStateFlow()


    fun updateActiveIngredient(value: String) {
        _state.update { it.copy(activeIngredientAmountMg = value.toDoubleOrNull() ?: 0.0) }
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

    fun calculateResult() {
        if (!allInputsAreValid()) return
        val singleDose = calculateTabletByWeightSingleDose(_state.value)
        val tabletsQuantity =
            calculateTabletsQuantity(
                singleDose = singleDose,
                activeIngredientAmountMg = _state.value.activeIngredientAmountMg
            )
        _state.update {
            it.copy(
                singleDoseMg = singleDose,
                tabletsQuantity = tabletsQuantity
            )
        }
    }

    private fun allInputsAreValid(): Boolean {
        with(_state.value) {
            return activeIngredientAmountMg > 0
                    && weight > 0
                    && dosePerKg > 0
                    && if (dailyOrSingle == DailyOrSingle.DAILY.ordinal) dosesPerDay > 0 else true
        }
    }

    private fun resetResult() {
        _state.update { it.copy(singleDoseMg = 0.0, tabletsQuantity = 0.0) }
    }
}