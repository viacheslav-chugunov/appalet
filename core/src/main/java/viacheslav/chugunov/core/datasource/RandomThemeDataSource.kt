package viacheslav.chugunov.core.datasource

import viacheslav.chugunov.core.model.Theme

interface RandomThemeDataSource {
    fun create(isLight: Boolean): Theme
}