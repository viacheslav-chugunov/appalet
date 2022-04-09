package viacheslav.chugunov.core.datasource

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

interface DatabaseDataSource<T> {
    suspend fun get() : List<T>
    fun getFlow() : Flow<List<T>>
    suspend fun add(items: List<T>)
    suspend fun add(vararg items: T) = add(items.toList())
    suspend fun remove(items: List<T>)
    suspend fun remove(vararg items: T) = remove(items.toList())
    suspend fun clear()
}