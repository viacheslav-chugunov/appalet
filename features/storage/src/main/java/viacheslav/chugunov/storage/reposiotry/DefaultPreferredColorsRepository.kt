package viacheslav.chugunov.storage.reposiotry

import android.util.Log
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors

class DefaultPreferredColorsRepository(
    private val preference: PreferenceDataSource<DataStorePreferredColors>,
    private val defaultColors: DataStorePreferredColors = DataStorePreferredColors()
) : PreferredColorsRepository {

    override fun getColorsFlow(): Flow<PreferredColors> =
        preference.getFlow().map { (it ?: defaultColors).asPreferredColors() }

    override suspend fun setLightBackground(color: Coloring) =
        setColors { PreferredColors.Default(color, it.darkBackground) }

    override suspend fun setDarkBackground(color: Coloring) =
        setColors { PreferredColors.Default(it.lightBackground, color) }

    override suspend fun resetLightBackground() =
        setColors { PreferredColors.Default(darkBackground = it.darkBackground) }

    override suspend fun resetDarkBackground() =
        setColors { PreferredColors.Default(lightBackground = it.lightBackground) }

    private suspend fun setColors(map: (current: PreferredColors) -> PreferredColors) {
        val currentColors = preference.get() ?: defaultColors
        val newColors = map(currentColors.asPreferredColors())
        preference.set(DataStorePreferredColors(newColors))
    }
}