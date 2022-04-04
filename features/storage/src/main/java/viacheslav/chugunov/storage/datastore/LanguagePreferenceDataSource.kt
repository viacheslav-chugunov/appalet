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
    serializer: Serializer
) : DefaultPreferenceDataSource<Language>(
    preference = context.preference,
    serializer = serializer,
    keyName = "language"
) {
    override val defaultData: Language = Language.English
    override val dataClass: Class<Language.Default> = Language.Default::class.java

    companion object {
        val Context.preference: DataStore<Preferences> by preferencesDataStore(name = "language")
    }
}