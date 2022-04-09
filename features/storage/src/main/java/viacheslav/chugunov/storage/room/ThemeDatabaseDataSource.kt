package viacheslav.chugunov.storage.room

import kotlinx.coroutines.flow.Flow
import viacheslav.chugunov.core.datasource.DatabaseDataSource
import viacheslav.chugunov.core.model.Theme

class ThemeDatabaseDataSource(
    private val dao: ThemeDao
) : DatabaseDataSource<ThemeEntity> {
    override suspend fun get(): List<ThemeEntity> = dao.get()
    override fun getFlow(): Flow<List<ThemeEntity>> = dao.getFlow()
    override suspend fun add(items: List<ThemeEntity>) = dao.add(items)
    override suspend fun remove(items: List<ThemeEntity>) = dao.remove(items)
    override suspend fun clear() = dao.clear()
}