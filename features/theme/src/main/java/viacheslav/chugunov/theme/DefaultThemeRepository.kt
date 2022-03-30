package viacheslav.chugunov.theme

import viacheslav.chugunov.core.datasource.RandomThemeDataSource
import viacheslav.chugunov.core.model.SelectedTheme
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.ThemeRepository

class DefaultThemeRepository(
    private val randomThemeDataSource: RandomThemeDataSource
) : ThemeRepository {

    override fun getRandom(isLight: Boolean): Theme =
        randomThemeDataSource.create(isLight)
}