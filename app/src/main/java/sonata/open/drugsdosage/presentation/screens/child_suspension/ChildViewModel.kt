package sonata.open.drugsdosage.presentation.screens.child_suspension

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.data.repository.MedicineRepository
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateChildSuspension
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.convertWeightToKg
import javax.inject.Inject

@HiltViewModel
class ChildViewModel @Inject constructor(
    val repository: MedicineRepository,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val medicineListState = repository.medicineListFlow

    private val _childState = MutableStateFlow(ChildState(Medicine()))
    val childState: StateFlow<ChildState> = _childState.asStateFlow()

    private var savedLanguage = ""

    fun getMedicineList(language: String) {
        // if app language has changed of the medicine list is empty -> fetch the list again
        if (medicineListState.value.isNotEmpty() && savedLanguage == language) return
        savedLanguage = language
        _isLoading.value = true
        viewModelScope.launch {
            repository.getMedicineList(language)
            _isLoading.value = false
            Log.d("FAT_ChildViewModel", "VM LOADED list from repository")
        }
    }

    init {
        viewModelScope.launch {
            medicineListState.collect { medicineList ->
                if (medicineList.isNotEmpty()) {
                    _childState.update { it.copy(medicine = medicineList[0]) }
                }
            }
        }
    }

    fun updateWeight(weight: String) {
        _childState.update { it.copy(weight = weight.toDoubleOrNull() ?: 0.0) }
        resetSingleDose()
    }

    fun updateMedicine(medicine: Medicine) {
        _childState.update { it.copy(medicine = medicine) }
        resetSingleDose()
    }

    fun updateWeightUnits(units: String) {
        _childState.update { it.copy(weightUnits = units) }
        resetSingleDose()
    }

    fun calculateSingleDose() {
        updateWeightValidity()
        if (!_childState.value.isValidWeight) return
        val weight = convertWeightToKg(_childState.value)
        val result = calculateChildSuspension(_childState.value.medicine, weight)
        _childState.update {
            it.copy(singleDose = result)
        }
    }

    private fun updateWeightValidity() {
        _childState.update {
            it.copy(isValidWeight = it.weight > 0)
        }
    }

    private fun resetSingleDose() {
        _childState.update { it.copy(singleDose = 0.0) }
    }
}
