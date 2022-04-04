package viacheslav.chugunov.core.datasource

import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme

interface RandomThemeDataSource {
    fun createTheme(isLight: Boolean, preferredColors: PreferredColors): Theme
    fun createColor(): Coloring
}