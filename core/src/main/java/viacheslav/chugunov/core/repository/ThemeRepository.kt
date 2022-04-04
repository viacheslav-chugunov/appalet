package viacheslav.chugunov.core.repository

import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme

interface ThemeRepository {
    fun getRandomTheme(isLight: Boolean, preferredColors: PreferredColors): Theme
    fun getRandomColor(): Coloring
}