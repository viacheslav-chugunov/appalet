package viacheslav.chugunov.storage.reposiotry

import android.util.Log
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.repository.PreferredColorsRepository

class DefaultPreferredColorsRepository(
    private val preference: PreferenceDataSource<PreferredColors>,
    private val defaultColors: PreferredColors = PreferredColors.Default()
) : PreferredColorsRepository {

    override fun getColorsFlow(): Flow<PreferredColors> =
        preference.getFlow().map { it ?: defaultColors }

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
        val newColors = map(currentColors)
        preference.set(newColors)
    }
}