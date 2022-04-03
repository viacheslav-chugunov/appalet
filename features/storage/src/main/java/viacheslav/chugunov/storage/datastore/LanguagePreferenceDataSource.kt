package viacheslav.chugunov.storage.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.util.Serializer

class LanguagePreferenceDataSource(
    context: Context,
    private val serializer: Serializer
) : PreferenceDataSource.Language {
    private val preference = context.preference

    override fun getFlow(): Flow<Language> {
        return preference.data.map {
            val language = it[KEY]
            serializer.fromStringOrDefault(
                language,
                Language.Default::class.java,
                Language.English
            )
        }
    }

    override suspend fun set(data: Language) {
        preference.edit { it[KEY] = serializer.toString(data) }
    }

    override suspend fun clear() {
        preference.edit { it.clear() }
    }

    companion object {
        val Context.preference: DataStore<Preferences> by preferencesDataStore(name = "language")
        val KEY = stringPreferencesKey("language")
    }
}