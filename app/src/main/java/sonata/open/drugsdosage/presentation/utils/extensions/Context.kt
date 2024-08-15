package sonata.open.drugsdosage.presentation.utils.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

// DataStore
const val DATASTORE = "rate_countdown"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)