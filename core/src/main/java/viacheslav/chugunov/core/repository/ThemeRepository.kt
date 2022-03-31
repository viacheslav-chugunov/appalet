package viacheslav.chugunov.core.repository

import viacheslav.chugunov.core.model.domain.Theme

interface ThemeRepository {
    fun getRandom(isLight: Boolean): Theme
}