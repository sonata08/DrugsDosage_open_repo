package sonata.open.drugsdosage.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sonata.open.drugsdosage.data.datastore.DataStorePreferences
import sonata.open.drugsdosage.presentation.utils.extensions.dataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return appContext.dataStore
    }

    @Provides
    @Singleton
    fun provideDataStorePreferences(dataStore: DataStore<Preferences>): DataStorePreferences {
        return DataStorePreferences(dataStore)
    }
}