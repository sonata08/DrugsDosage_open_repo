package sonata.open.drugsdosage.data.repository.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import sonata.open.drugsdosage.Constants.LANGUAGE_RU
import sonata.open.drugsdosage.Constants.LANGUAGE_UK
import sonata.open.drugsdosage.data.model.Medicine
import sonata.open.drugsdosage.data.repository.MedicineRepository
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(): MedicineRepository {

    private val _medicineListFlow = MutableStateFlow<List<Medicine>>(emptyList())
    override val medicineListFlow = _medicineListFlow.asStateFlow()

    override suspend fun getMedicineList(language: String) =
        when (language) {
            LANGUAGE_UK -> _medicineListFlow.value = medicineListUk
            LANGUAGE_RU -> _medicineListFlow.value = medicineListRu
            else -> _medicineListFlow.value = medicineListEn
        }

    companion object {
        val medicineListUk = listOf(
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
                name = "Ібуфен",
                activeIngredient = "Ібупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат застосовують дітям віком від 3 місяців до 12 років з масою тіла не менше 5 кг."
            ),
            Medicine(
                name = "Ібуфен Форте",
                activeIngredient = "Ібупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 200,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат не застосовують дітям молодшим 6 місяців з масою тіла менше 8 кг."
            ),
            Medicine(
                name = "Імет для дітей 2%",
                activeIngredient = "Ібупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат Імет для дітей 2% призначений для застосування у дітей з масою тіла від 5 кг (6 міс) до 29 кг (9 років)."
            ),
            Medicine(
                name = "Імет для дітей 4%",
                activeIngredient = "Ібупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 200,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат Імет для дітей 4 % призначений для застосування дітям з масою тіла від 10 кг (віком від 1 року)."
            ),
            Medicine(
                name = "Бофен",
                activeIngredient = "Ібупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат застосовувати дітям віком від 3 місяців (з масою тіла не менше 5 кг) до 12 років"
            ),
            Medicine(
                name = "Ібупрофен",
                activeIngredient = "Ібупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат застосовують дітям віком від 3 місяців до 12 років з масою тіла не менше 5 кг."
            ),
            Medicine(
                name = "Ібупрофен",
                activeIngredient = "Ібупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 200,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат застосовують дітям віком від 3 місяців до 12 років з масою тіла не менше 5 кг."
            ),
            Medicine(
                name = "Ібупрофен",
                activeIngredient = "Ібупрофен",
                medicineAmountMl = 1.25,
                activeIngredientAmountMg = 50,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат застосовують дітям віком від 3 місяців до 12 років з масою тіла не менше 5 кг."
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
                info = ""
            ),
            Medicine(
                name = "Парацетамол",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 160,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Препарат протипоказаний для застосування дітям віком до 6 місяців. Дана лікарська форма застосовується дітям віком від 6 місяців до 12 років."
            ),
            Medicine(
                name = "Ефералган",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 1.0,
                activeIngredientAmountMg = 30,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 6,
                info = "Препарат застосовувати дітям з масою тіла від 4 до 32 кг (від 1 місяця до 12 років)"
            ),
            Medicine(
                name = "Панадол бебі",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 120,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Не застосовувати препарат Панадол бебі дітям віком до 3 місяців. Рекомендується застосовувати дітям віком від 3 місяців до 12 років"
            ),
            Medicine(
                name = "Піарон",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 120,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Призначений для лікування дітей віком від 2 місяців до 12 років. Маса тіла дитини має бути більше 4 кг."
            ),
        )

        val medicineListRu = listOf(
            Medicine(
                name = "Нурофен",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат применяют детям в возрасте от 3 месяцев до 12 лет с массой тела не менее 5 кг"
            ),
            Medicine(
                name = "Нурофен Форте",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 200,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат применяют детям в возрасте от 6 месяцев, масса тела которых не менее 8 кг, до 12 лет"
            ),
            Medicine(
                name = "Ибуфен",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат применяют детям в возрасте от 3 месяцев до 12 лет с массой тела не менее 5 кг"
            ),
            Medicine(
                name = "Ибуфен Форте",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 200,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат не применяют детям младше 6 месяцев с массой тела менее 8 кг"
            ),
            Medicine(
                name = "Бофен",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат применять детям в возрасте от 3 месяцев (с массой тела не менее 5 кг) до 12 лет"
            ),
            Medicine(
                name = "Ибупрофен",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат применяют детям в возрасте от 3 месяцев до 12 лет с массой тела не менее 5 кг"
            ),
            Medicine(
                name = "Ибупрофен",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 200,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат применяют детям в возрасте от 3 месяцев до 12 лет с массой тела не менее 5 кг"
            ),
            Medicine(
                name = "Ибупрофен",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 1.25,
                activeIngredientAmountMg = 50,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат применяют детям в возрасте от 3 месяцев до 12 лет с массой тела не менее 5 кг"
            ),
            Medicine(
                name = "Парацетамол",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 120,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Препарат противопоказан детям в возрасте до 6 месяцев.\nДанная лекарственная форма применяется детям в возрасте от 6 месяцев до 12 лет"
            ),
            Medicine(
                name = "Парацетамол",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 250,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = ""
            ),
            Medicine(
                name = "Парацетамол",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 160,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Препарат противопоказан детям в возрасте до 6 месяцев.\nДанная лекарственная форма применяется детям в возрасте от 6 месяцев до 12 лет"
            ),
            Medicine(
                name = "Эффералган для детей",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 1.0,
                activeIngredientAmountMg = 30,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 6,
                info = "Препарат применять детям с массой тела от 4 до 32 кг (от 1 месяца до 12 лет)"
            ),
            Medicine(
                name = "Панадол беби",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 120,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Не использовать Панадол бэби детям в возрасте до 3 месяцев.\nРекомендуется применять детям в возрасте от 3 месяцев до 12 лет"
            ),
            Medicine(
                name = "Пиарон",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 120,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Предназначен для лечения детей в возрасте от 2 месяцев до 12 лет. Масса тела ребенка должна быть более 4 кг"
            ),
            Medicine(
                name = "Имет для детей 2%",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат Имет для детей 2% предназначен для использования у детей с массой тела от 5 кг (6 мес) до 29 кг (9 лет)."
            ),
            Medicine(
                name = "Имет для детей 4%",
                activeIngredient = "Ибупрофен",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 200,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = "Препарат Имет для детей 4% предназначен для применения детям с массой тела от 10 кг (возрастом от 1 года)."
            ),
        )

        val medicineListEn = listOf(
            Medicine(
                name = "Ibuprofen",
                activeIngredient = "Ibuprofen",
                medicineAmountMl = 1.25,
                activeIngredientAmountMg = 50,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = " "
            ),
            Medicine(
                name = "Ibuprofen",
                activeIngredient = "Ibuprofen",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 100,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = " "
            ),
            Medicine(
                name = "Ibuprofen",
                activeIngredient = "Ibuprofen",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 200,
                maxDailyDose = 30,
                maxDosesPerDay = 3,
                minDosingInterval = 6,
                info = " "
            ),
            Medicine(
                name = "Paracetamol",
                activeIngredient = "Paracetamol",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 120,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Caution must be exerted when the weight-based dosing is applied to children weighing over 65 kg (143 lbs) so that daily adult paracetamol dose (4 g/day) is not exceeded."
            ),
            Medicine(
                name = "Paracetamol",
                activeIngredient = "Paracetamol",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 250,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Caution must be exerted when the weight-based dosing is applied to children weighing over 65 kg (143 lbs) so that daily adult paracetamol dose (4 g/day) is not exceeded."
            ),
            Medicine(
                name = "Paracetamol",
                activeIngredient = "Paracetamol",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 160,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Caution must be exerted when the weight-based dosing is applied to children weighing over 65 kg (143 lbs) so that daily adult paracetamol dose (4 g/day) is not exceeded."
            )
        )
    }
}