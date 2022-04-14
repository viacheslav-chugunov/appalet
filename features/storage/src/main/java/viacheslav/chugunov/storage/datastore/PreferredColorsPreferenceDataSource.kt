package viacheslav.chugunov.storage.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.util.Serializer
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors

class PreferredColorsPreferenceDataSource(
    context: Context,
    serializer: Serializer
) : DefaultPreferenceDataSource<DataStorePreferredColors>(
    preference = context.preference,
    serializer = serializer,
    keyName = "preferredColors"
) {
    override val defaultData: DataStorePreferredColors = DataStorePreferredColors()
    override val dataClass: Class<DataStorePreferredColors> = DataStorePreferredColors::class.java

    companion object {
        val Context.preference: DataStore<Preferences> by preferencesDataStore(name = "preferred-colors")
    }
}