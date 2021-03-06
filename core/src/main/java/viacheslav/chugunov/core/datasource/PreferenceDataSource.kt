package viacheslav.chugunov.core.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import viacheslav.chugunov.core.model.PreferredColors

interface PreferenceDataSource<T> {
    fun getFlow(): Flow<T?>
    suspend fun set(data: T)
    suspend fun clear()
    suspend fun get(): T? = getFlow().first()
}