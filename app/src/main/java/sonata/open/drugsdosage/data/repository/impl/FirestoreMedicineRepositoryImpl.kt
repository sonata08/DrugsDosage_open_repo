package sonata.open.drugsdosage.data.repository.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import sonata.open.drugsdosage.Constants.LANGUAGE_RU
import sonata.open.drugsdosage.Constants.LANGUAGE_UK
import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.data.repository.AnalyticsRepository
import sonata.open.drugsdosage.data.repository.MedicineRepository
import javax.inject.Inject

class FirestoreMedicineRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val analytics: AnalyticsRepository,
) : MedicineRepository {

    private val _medicineListFlow = MutableStateFlow<List<Medicine>>(emptyList())
    override val medicineListFlow = _medicineListFlow.asStateFlow()

    override suspend fun getMedicineList(language: String) {
        val collectionName = when (language) {
            LANGUAGE_UK -> DRUGS_UK
            LANGUAGE_RU -> DRUGS_RU
            else -> DRUGS_EN
        }
        withContext(Dispatchers.IO) {
            db.collection(collectionName)
                .orderBy(ACTIVE_INGREDIENT)
                .orderBy(NAME)
                .get()
                .addOnSuccessListener { result ->
                    _medicineListFlow.value =
                        result.documents.mapNotNull { it.toObject<Medicine>() }
                }
                .addOnFailureListener { exception ->
                    analytics.logEvent("firestore_error")
                    _medicineListFlow.value = emptyList()
                }
        }
    }

    companion object {
        const val DRUGS_UK = "drugs_uk"
        const val DRUGS_EN = "drugs_en"
        const val DRUGS_RU = "drugs_ru"
        const val ACTIVE_INGREDIENT = "activeIngredient"
        const val NAME = "name"
    }
}
