package sonata.open.drugsdosage.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class DataStorePreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun getCounter(): Int {
        val prefs = dataStore.data.first()
        return prefs[COUNTER] ?: 0
    }

    suspend fun incrementCounter() {
        dataStore.edit { preferences ->
            val currentCounterValue = preferences[COUNTER] ?: 0
            preferences[COUNTER] = currentCounterValue + 1
        }
    }

    suspend fun resetCounter() {
        dataStore.edit { preferences ->
            preferences[COUNTER] = 0
        }
    }

    companion object {
        // counts number of times the app was opened
        private val COUNTER = intPreferencesKey("counter")
    }
}