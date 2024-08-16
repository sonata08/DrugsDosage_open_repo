package sonata.open.drugsdosage.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sonata.open.drugsdosage.data.repository.MedicineRepository
import sonata.open.drugsdosage.data.repository.TimerRepository
import sonata.open.drugsdosage.data.repository.impl.MedicineRepositoryImpl
import sonata.open.drugsdosage.data.repository.impl.TimerRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTimerRepository(timerRepositoryImpl: TimerRepositoryImpl): TimerRepository

    @Binds
    @Singleton
    abstract fun bindMedicineRepository(medicineRepositoryImpl: MedicineRepositoryImpl): MedicineRepository
}