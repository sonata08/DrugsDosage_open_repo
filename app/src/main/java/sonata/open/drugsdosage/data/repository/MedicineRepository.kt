package sonata.open.drugsdosage.data.repository

import kotlinx.coroutines.flow.StateFlow
import sonata.open.drugsdosage.data.model.Medicine

interface MedicineRepository {
    suspend fun getMedicineList(language: String)
    val medicineListFlow: StateFlow<List<Medicine>>
}