package viacheslav.chugunov.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import viacheslav.chugunov.core.datasource.DatabaseDataSource
import kotlin.reflect.KClass

@Database(
    version = 1,
    entities = [ThemeEntity::class])
abstract class AppRoomDatabase : RoomDatabase() {
    abstract val themeDao: ThemeDao

    companion object {
        private var INSTANCE: AppRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppRoomDatabase = INSTANCE ?: Room
            .databaseBuilder(context, AppRoomDatabase::class.java, "appalet-database")
            .build()
            .also { INSTANCE = it }
    }
}