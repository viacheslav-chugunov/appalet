package viacheslav.chugunov.storage.reposiotry

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex
import viacheslav.chugunov.core.datasource.DatabaseDataSource
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.storage.room.ThemeEntity

class DefaultPreferredThemesRepository(
    private val database: DatabaseDataSource<ThemeEntity>
) : PreferredThemesRepository {

    override fun getThemesFlow(): Flow<List<Theme>> =
        database.getFlow()

    override suspend fun addTheme(theme: Theme) {
        database.add(ThemeEntity(theme))
    }

    override suspend fun removeTheme(theme: Theme) {
        database.get().find { it == theme }?.let { database.remove(it) }
    }

    override suspend fun isThemeAdded(theme: Theme): Boolean =
        database.get().find { theme == it } != null
}