package viacheslav.chugunov.core.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import viacheslav.chugunov.core.model.Theme

interface PreferredThemesRepository {
    fun getThemesFlow(): Flow<List<Theme>>
    fun getThemesNow(): List<Theme> = runBlocking { getThemes() }
    suspend fun getThemes(): List<Theme> = getThemesFlow().first()
    suspend fun addTheme(theme: Theme)
    suspend fun removeTheme(theme: Theme)
    suspend fun isThemeAdded(theme: Theme): Boolean
}