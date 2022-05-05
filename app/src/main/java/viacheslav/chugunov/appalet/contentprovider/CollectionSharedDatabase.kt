package viacheslav.chugunov.appalet.contentprovider

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import viacheslav.chugunov.core.model.Theme

class CollectionSharedDatabase(
    context: Context,
    private val themes: List<Theme>
) : SQLiteOpenHelper(context, TABLE_NAME, null, 1) {

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(
            """CREATE TABLE IF NOT EXISTS $TABLE_NAME(
            |$ID INTEGER PRIMARY KEY AUTOINCREMENT,
            |PRIMARY_LIGHT INTEGER NOT NULL,
            |$PRIMARY_REGULAR INTEGER NOT NULL,
            |$PRIMARY_DARK INTEGER NOT NULL,
            |$SECONDARY_LIGHT INTEGER NOT NULL,
            |$SECONDARY_REGULAR INTEGER NOT NULL,
            |$SECONDARY_DARK INTEGER NOT NULL,
            |$BACKGROUND INTEGER NOT NULL,
            |$ON_PRIMARY_LIGHT INTEGER NOT NULL,
            |$ON_PRIMARY_REGULAR INTEGER NOT NULL,
            |$ON_PRIMARY_DARK INTEGER NOT NULL,
            |$ON_SECONDARY_LIGHT INTEGER NOT NULL,
            |$ON_SECONDARY_REGULAR INTEGER NOT NULL,
            |$ON_SECONDARY_DARK INTEGER NOT NULL,
            |$ON_BACKGROUND INTEGER NOT NULL
            |)""".trimMargin()
        )
        val values = ContentValues(themes.size)
        themes.forEachIndexed { index, theme ->
            values.put(ID, index + 1)
            values.put(PRIMARY_LIGHT, theme.colorsPrimary.light.value)
            values.put(PRIMARY_REGULAR, theme.colorsPrimary.regular.value)
            values.put(PRIMARY_DARK, theme.colorsPrimary.dark.value)
            values.put(SECONDARY_LIGHT, theme.colorsSecondary.light.value)
            values.put(SECONDARY_REGULAR, theme.colorsSecondary.regular.value)
            values.put(SECONDARY_DARK, theme.colorsSecondary.dark.value)
            values.put(BACKGROUND, theme.colorBackground.value)
            values.put(ON_PRIMARY_LIGHT, theme.colorsPrimary.onLight.value)
            values.put(ON_PRIMARY_REGULAR, theme.colorsPrimary.onRegular.value)
            values.put(ON_PRIMARY_DARK, theme.colorsPrimary.onDark.value)
            values.put(ON_SECONDARY_LIGHT, theme.colorsSecondary.onLight.value)
            values.put(ON_SECONDARY_REGULAR, theme.colorsSecondary.onRegular.value)
            values.put(ON_SECONDARY_DARK, theme.colorsSecondary.onDark.value)
            values.put(ON_BACKGROUND, theme.colorOnBackground.value)
            database.insert(TABLE_NAME, null, values)
        }
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) = Unit

    companion object {
        const val TABLE_NAME = "COLLECTION"

        const val ID = "ID"
        const val PRIMARY_LIGHT = "PRIMARY_LIGHT"
        const val PRIMARY_REGULAR = "PRIMARY_REGULAR"
        const val PRIMARY_DARK = "PRIMARY_DARK"
        const val SECONDARY_LIGHT = "SECONDARY_LIGHT"
        const val SECONDARY_REGULAR = "SECONDARY_REGULAR"
        const val SECONDARY_DARK = "SECONDARY_DARK"
        const val BACKGROUND = "BACKGROUND"
        const val ON_PRIMARY_LIGHT = "ON_PRIMARY_LIGHT"
        const val ON_PRIMARY_REGULAR = "ON_PRIMARY_REGULAR"
        const val ON_PRIMARY_DARK = "ON_PRIMARY_DARK"
        const val ON_SECONDARY_LIGHT = "ON_SECONDARY_LIGHT"
        const val ON_SECONDARY_REGULAR = "ON_SECONDARY_REGULAR"
        const val ON_SECONDARY_DARK = "ON_SECONDARY_DARK"
        const val ON_BACKGROUND = "ON_BACKGROUND"
    }
}