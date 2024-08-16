package sonata.open.drugsdosage.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sonata.open.drugsdosage.data.datastore.DataStorePreferences
import sonata.open.drugsdosage.data.repository.impl.ReviewRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ReviewRepositoryModule {

    @Provides
    @Singleton
    fun provideReviewRepository(dataStore: DataStorePreferences): ReviewRepositoryImpl {
        return ReviewRepositoryImpl(dataStore)
    }
}