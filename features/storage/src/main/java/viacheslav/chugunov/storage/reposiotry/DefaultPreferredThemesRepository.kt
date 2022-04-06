package viacheslav.chugunov.storage.reposiotry

import kotlinx.coroutines.flow.Flow
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.storage.room.ThemeDao
import viacheslav.chugunov.storage.room.ThemeDatabaseDataSource
import viacheslav.chugunov.storage.room.ThemeEntity

class DefaultPreferredThemesRepository(
    private val themeDatabase: ThemeDatabaseDataSource
) : PreferredThemesRepository {
    override suspend fun getThemesFlow(): Flow<List<Theme>> = themeDatabase.getFlow()

    override suspend fun addTheme(theme: Theme) {
        val entity = ThemeEntity(theme)
        themeDatabase.add(entity)
    }

    override suspend fun removeTheme(theme: Theme) {
        if (theme is ThemeEntity) {
            themeDatabase.remove(theme)
        } else {
            themeDatabase.get().forEach { stored ->
                if (stored == theme) {
                    themeDatabase.remove(stored)
                    return@removeTheme
                }
            }
        }
    }
}