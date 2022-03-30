package viacheslav.chugunov.core.repository

import viacheslav.chugunov.core.model.SelectedTheme
import viacheslav.chugunov.core.model.Theme

interface ThemeRepository {
    fun getRandom(isLight: Boolean): Theme
}