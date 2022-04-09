package viacheslav.chugunov.storage.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.util.Serializer
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors

class PreferredColorsPreferenceDataSource(
    context: Context,
    serializer: Serializer
) : DefaultPreferenceDataSource<PreferredColors>(
    preference = context.preference,
    serializer = serializer,
    keyName = "preferredColors"
) {
    override val defaultData: PreferredColors = PreferredColors.Default()
    override val dataClass: Class<DataStorePreferredColors> = DataStorePreferredColors::class.java

    companion object {
        val Context.preference: DataStore<Preferences> by preferencesDataStore(name = "preferred-colors")
    }
}