package sonata.open.drugsdosage.presentation.screens.tablet

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import sonata.open.drugsdosage.presentation.utils.UiConstants.G_TO_MG_FACTOR
import sonata.open.drugsdosage.presentation.utils.UiConstants.UNITS_G_EN
import sonata.open.drugsdosage.presentation.utils.UiConstants.UNITS_G_UK
import sonata.open.drugsdosage.presentation.utils.extensions.round
import javax.inject.Inject

@HiltViewModel
class TabletViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(TabletState())
    val state: StateFlow<TabletState> = _state.asStateFlow()

    fun updateDoseDoctor(dose: String) {
        _state.update { it.copy(doseByDoctor = dose.toDoubleOrNull() ?: 0.0) }
        resetSingleDose()
    }

    fun updateDoseAvailable(dose: String) {
        _state.update { it.copy(doseAvailable = dose.toDoubleOrNull() ?: 0.0) }
        resetSingleDose()
    }

    fun updateUnitDoctor(unit: String) {
        _state.update { it.copy(unitDoctor = unit) }
        resetSingleDose()
    }

    fun updateUnitAvailable(unit: String) {
        _state.update { it.copy(unitAvailable = unit) }
        resetSingleDose()
    }

    fun calculateSingleDose() {
        if (!allInputsAreValid()) return
        with(_state.value) {
            val dosageDoctor = convertToMg(doseByDoctor, unitDoctor)
            val dosageAvailable = convertToMg(doseAvailable, unitAvailable)
            val singleDose = (dosageDoctor / dosageAvailable).round()
            _state.update { it.copy(singleDose = singleDose) }
        }
    }

    private fun allInputsAreValid(): Boolean {
        with(_state.value) {
            return doseByDoctor > 0 && doseAvailable > 0
        }
    }

    // if unit == "g" -> convert dose to "mg"
    private fun convertToMg(dose: Double, unit: String): Double {
        return when(unit) {
            UNITS_G_EN -> dose * G_TO_MG_FACTOR
            UNITS_G_UK -> dose * G_TO_MG_FACTOR
            else -> dose
        }
    }

    private fun resetSingleDose() {
        _state.update { it.copy(singleDose = 0.0) }
    }
}