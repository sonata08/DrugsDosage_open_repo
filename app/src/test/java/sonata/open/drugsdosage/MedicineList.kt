package sonata.open.drugsdosage

import sonata.open.drugsdosage.data.model.Medicine

object MedicineList {
    val medicineList = listOf(
        MedicineWithResult(
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
            weight = 23,
            expectedResult = 11.5
        ),
        MedicineWithResult(
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
            weight = 23,
            expectedResult = 5.7
        ),
        MedicineWithResult(
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
            weight = 5,
            expectedResult = 2.5
        ),
        MedicineWithResult(
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
            weight = 5,
            expectedResult = 1.2
        ),
        MedicineWithResult(
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
            weight = 51,
            expectedResult = 12.7
        ),
        MedicineWithResult(
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
            weight = 100,
            expectedResult = 62.5
        ),
        MedicineWithResult(
            Medicine(
                name = "Парацетамол",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 250,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = "Препарат протипоказаний для застосування дітям віком до 6 місяців. Дана лікарська форма застосовується дітям віком від 6 місяців до 12 років."
            ),
            weight = 1,
            expectedResult = 0.3
        ),
        MedicineWithResult(
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
            weight = 23,
            expectedResult = 10.7
        ),
        MedicineWithResult(
            Medicine(
                name = "Парацетамол",
                activeIngredient = "Парацетамол",
                medicineAmountMl = 5.0,
                activeIngredientAmountMg = 160,
                maxDailyDose = 60,
                maxDosesPerDay = 4,
                minDosingInterval = 4,
                info = ""
            ),
            weight = 100,
            expectedResult = 46.8
        ),
        MedicineWithResult(
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
            weight = 1,
            expectedResult = 0.5
        ),
        MedicineWithResult(
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
            weight = 23,
            expectedResult = 14.3
        ),
        MedicineWithResult(
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
            weight = 51,
            expectedResult = 31.8
        ),
    )
}
