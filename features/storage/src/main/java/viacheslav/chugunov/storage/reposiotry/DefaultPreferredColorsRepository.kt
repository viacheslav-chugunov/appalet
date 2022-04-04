package viacheslav.chugunov.storage.reposiotry

import kotlinx.coroutines.flow.Flow
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.repository.PreferredColorsRepository

class DefaultPreferredColorsRepository(
    private val preference: PreferenceDataSource<PreferredColors>
) : PreferredColorsRepository {

    override fun getColorsFlow(): Flow<PreferredColors> =
        preference.getFlow()

    override suspend fun setLightBackground(color: Coloring) =
        setColors { PreferredColors.Default(color, it.darkBackground) }

    override suspend fun setDarkBackground(color: Coloring) =
        setColors { PreferredColors.Default(it.lightBackground, color) }

    override suspend fun resetLightBackground() =
        setColors { PreferredColors.Default(darkBackground = it.darkBackground) }

    override suspend fun resetDarkBackground() =
        setColors { PreferredColors.Default(lightBackground = it.lightBackground) }

    private suspend fun setColors(map: (current: PreferredColors) -> PreferredColors) {
        val currentColors = preference.get()
        val newColors = map(currentColors)
        preference.set(newColors)
    }
}