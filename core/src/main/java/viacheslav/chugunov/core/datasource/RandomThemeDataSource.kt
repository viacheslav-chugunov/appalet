package viacheslav.chugunov.core.datasource

import viacheslav.chugunov.core.model.domain.Theme

interface RandomThemeDataSource {
    fun create(isLight: Boolean): Theme
}