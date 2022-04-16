package viacheslav.chugunov.tests.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.storage.room.ThemeDao
import viacheslav.chugunov.storage.room.ThemeEntity

class TestThemeDao(themes: List<Theme> = emptyList()) : ThemeDao {
    private val themeEntities: MutableList<ThemeEntity> =
        themes.map { ThemeEntity(it) }.toMutableList()
    val themes: List<Theme> = themeEntities

    override fun get(): List<ThemeEntity> {
        return themeEntities
    }

    override fun getFlow(): Flow<List<ThemeEntity>> {
        return flowOf(themeEntities)
    }

    override fun add(themes: List<ThemeEntity>) {
        themeEntities += themes
    }

    override fun remove(items: List<ThemeEntity>) {
        themeEntities -= items
    }

    override fun clear() {
        themeEntities.clear()
    }
}