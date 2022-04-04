package viacheslav.chugunov.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.util.Serializer

abstract class DefaultPreferenceDataSource<T>(
    private val preference: DataStore<Preferences>,
    private val serializer: Serializer,
    keyName: String
) : PreferenceDataSource<T> {
    private val key = stringPreferencesKey(keyName)

    protected abstract val defaultData: T
    protected abstract val dataClass: Class<out T>

    override fun getFlow(): Flow<T> {
        return preference.data.map {
            val dataString = it[key]
            serializer.fromStringOrDefault(
                dataString,
                dataClass,
                defaultData
            )
        }
    }

    override suspend fun set(data: T) {
        preference.edit { it[key] = serializer.toString(data) }
    }

    override suspend fun clear() {
        preference.edit { it.clear() }
    }
}