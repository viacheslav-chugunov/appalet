package viacheslav.chugunov.theme

import viacheslav.chugunov.core.datasource.RandomThemeDataSource
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.ThemeRepository

class DefaultThemeRepository(
    private val randomThemeDataSource: RandomThemeDataSource
) : ThemeRepository {

    override fun getRandomTheme(isLight: Boolean, preferredColors: PreferredColors): Theme =
        randomThemeDataSource.createTheme(isLight, preferredColors)

    override fun getRandomColor(): Coloring =
        randomThemeDataSource.createColor()
}