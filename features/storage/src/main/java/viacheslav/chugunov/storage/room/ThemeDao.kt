package viacheslav.chugunov.storage.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import viacheslav.chugunov.core.model.Theme

@Dao
interface ThemeDao {

    @Query("SELECT * FROM THEMES ORDER BY TIME_ADDED ASC")
    fun get(): List<ThemeEntity>

    @Query("SELECT * FROM THEMES ORDER BY TIME_ADDED ASC")
    fun getFlow(): Flow<List<ThemeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(themes: List<ThemeEntity>)

    @Delete
    fun remove(items: List<ThemeEntity>)

    @Query("DELETE FROM THEMES")
    fun clear()
}